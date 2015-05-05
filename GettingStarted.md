# Freud has moved to Github #

**This version is old and is not actively maintained**

**see: https://github.com/LMAX-Exchange/freud**

## Getting Started with Freud ##

Freud static analysis is done by building a
[FreudAnalyser](http://code.google.com/p/freud/source/browse/trunk/src/main/org/langera/freud/core/FreudAnalyser.java) class.

We build it by specifying the rule we want to assert on the content
in a "declarative" way.

Running the  `FreudAnalyser ` is done by calling its single method:

```

void analyse(AnalysisListener listener);

```

The [AnalysisListener](http://code.google.com/p/freud/source/browse/trunk/src/main/org/langera/freud/core/listener/AnalysisListener.java) should define what to do with the results.

When building the  `FreudAnalyser ` we pass in an **iterator** of the content to analyse, and **assertions** on that content.

For example:

Lets create a simple analysis check that asserts that the length of a line in a text is not more than 80.

In Freud it will look like this:

```
Freud.iterateOver(TextLine.class).in(iterator).
                assertThat(lineLength().lessThanOrEqualTo(80));
```

We will iterate over `TextLine` objects and assert their length.

And to run it we will write:

```

final FreudAnalyser freudAnalyser =      
             Freud.iterateOver(TextLine.class).in(iterator).
                     assertThat(lineLength().lessThanOrEqualTo(80));
freudAnalyser.analyse(listener);

```

**listener** can be any class that implements [AnalysisListener](http://code.google.com/p/freud/source/browse/trunk/src/main/org/langera/freud/core/listener/AnalysisListener.java).

A few common implementations (like one that will throw  `org.junit.AssertionError` or one that prints the results to  `System.out`)
are provided with Freud.

Its time to dive into the details of this example:

```
Freud.iterateOver(TextLine.class)
```

describes... well... the object you are going to iterate over obviously.

```
  ... .in(iterator)
```

is how you supply the iterator for that object. In our example,

iterator must be of type `java.util.Iterator<TextLine>`

The iterator implementation defines how to get the content (text in this case) and parse it into `TextLine` objects.

The iterator can be created in several ways depending on what the content is and how can we access it.

Supplying an iterator means you need to access the text (in this case) and "parse" it into `TextLine` objects. But Freud gives you also "parsers" to all its supported content types and an easy way to define iterators which should make it trivial in all common cases.

Another important aspect is the ability to convert from one content type
to another. http://code.google.com/p/freud/source/browse/trunk/src/main/org/langera/freud/optional/text/textline/TextLine.java TextLine] objects can be derived from
[Text](http://code.google.com/p/freud/source/browse/trunk/src/main/org/langera/freud/optional/text/Text.java) objects and so we can also define an iterator of Text objects (which is really just a wrapper on a byte array) and write:

```
Freud.iterateOver(TextLine.class).within(iterator, Text.class).
```

with an iterator of type `java.util.Iterator<Text>`.


The third option is to construct a Freud specific implementation of iterator called [AnalysedObjectIterator](http://code.google.com/p/freud/source/browse/trunk/src/main/org/langera/freud/core/iterator/AnalysedObjectIterator.java)

and then assuming iterator is of type `AnalysedObjectIterator<Text>`
you can write:

```
Freud.iterateOver(TextLine.class).within(iterator).
```

After the iterator, comes the assertion:

```
lineLength().lessThanOrEqualTo(80) 
```

is a hamcrest matcher which describes the assertion on the `TextLine` object.

Every content type has a DSL class with static methods that define common matchers for that content type.

Another option is to define a custom assertion. This is simply done as:

```
        return Freud.iterateOver(TextLine.class).within(iterator).
                assertThat(new TypeSafeMatcher<TextLine>()
                {
                    @Override
                    public boolean matchesSafely(final TextLine line)
                    {
                        return line.getLine().length() <= 80;
                    }

                    @Override
                    public void describeTo(final Description description)
                    {
                        description.appendText("line length <= 80");
                    }
                });

```


the Freud `DSL` - or group of predefined matchers is by no means complete
(Any additions / code patches / requests are welcome BTW), and there is no need to despair if what you want to assert is not already pre-defined.

That is the whole point - Freud lets you write just what you're really interested in - asserting your contract/convention.... no need to visit or traverse some `AST` nodes with some inside knowledge to be able to just say what you want.

### So - the text example is a bit simple.... is it just about finding line length or existance of some regexp in text? ###

well, here's a test that asserts that you will never call equals explicitly on a `java.math.BigDecimal` object:

```
Freud.iterateOver(ClassFileMethod.class).within(iterator).
                assertThat(no(hasMethodInvocation(BigDecimal.class, "equals", Object.class)));

```

> We found this extremely important. There is nothing wrong with the `BigDecimal` equals method. In some contexts it is what you want to call - but if you don't care about full equality of the object (ie. same scale etc.) and just wants to see whether the numeric value it represents is the same - equals is bad and you should use `compareTo`.


This example uses an object called `ClassFileMethod` - which is derived from an iterator of `ClassFile` objects.

The `ClassFile` object is achieved by iterating over .class files and parsing them using a parser built on top of the `ASM` framework.

### So can we only iterate over Freud specific objects then? ###

No. Here's an example of a test that will assert that either both equals and hashcode are defined for this class or none of them.

see [Effective java Chapter 3 item 9](http://java.sun.com/docs/books/effective/).

```
Freud.iterateOver(Class.class).in(iterator).
                assertThat(hasDeclaredMethod("equals", Object.class).and(hasDeclaredMethod("hashCode")).
                        or(no(hasDeclaredMethod("equals", Object.class)).and(no(hasDeclaredMethod("hashCode")))));
```

It simply iterates over `java.lang.Class` objects.

### Finally - is this just a java thing then??? ###

No its not. The project grew mainly with the needs we had at [work](http://www.LMAX.com). It does mean we support all possible options of analysing java code (`Class` view at runtime, class file and source file), and all the associated stuff you would use with a web application
(`CSS`, `properties files` and simple `Text` files).
We even have some tests to our `Spring` configuration using Freud.

Here's another example of a test that makes sure you're never qualifying an id CSS rule with a tag rule.

see [mozila's tips for efficient CSS](https://developer.mozilla.org/en/Writing_Efficient_CSS)

```
Freud.iterateOver(CssRule.class).within(iterator).
        assertThat(no(containsSelector(CssSelector.Type.TAG).and(
                lastIndexOfSelector(CssSelector.Type.TAG).lessThan(lastIndexOfSelector(CssSelector.Type.ID)))));
```

I'd love to extend this further (or accept patches for extensions).

I believe that there is a lot more room and value for static analysis in scripting or non type safe languages where you're not necessarily getting any help from a compiler. We just didn't used those in anger yet...