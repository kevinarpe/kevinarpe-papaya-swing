## Introduction ##

If you use Java Swing, I am working to extend existing widgets and add new utilities classes.  This library seeks not to subvert the intentions and work of the original Swing authors at Sun Microsystems, but rather to complement and enhance -- like sanding down rough edges.

Some examples:
  * All widgets with text labels, such as JLabel, JButton, JRadioButton, etc., will support mnemonic markers, e.g., "&Sample" will underline letter "S" and map shortcut Alt+S to the widget (or its buddy, in the case of JLabel).
  * All text editor widgets, such as JTextField and JTextArea, will have a default undo/redo handler, associated find/replace dialog, and a context menu that users expect in a modern GUI application.
  * Integer constants, such as those from interface [SwingConstants](http://docs.oracle.com/javase/6/docs/api/javax/swing/SwingConstants.html), are wrapped by new enumerated data types and new methods in the extended widgets provide support.
    * New enum: [PHorizontalAlignment](http://docs.kevinarpe-papaya-swing.googlecode.com/git/javadoc/com/googlecode/kevinarpe/papaya/swing/PHorizontalAlignment.html)
    * New method: [PJLabel.setHorizontalAlignment(PHorizontalAlignment)](http://docs.kevinarpe-papaya-swing.googlecode.com/git/javadoc/com/googlecode/kevinarpe/papaya/swing/widget/PJLabel.html#setHorizontalAlignment(com.googlecode.kevinarpe.papaya.swing.PHorizontalAlignment))
  * Support for icon themes used by KDE and GNOME to make decorating your application simpler.

This project targets Java 1.6+ and is closely tied to the project [kevinarpe-papaya](https://code.google.com/p/kevinarpe-papaya/).

## Testing ##

This library is well-tested and its test coverage is continually improving.

Every class and static method is marked with Java annotations as either [@NotFullyTested](http://docs.kevinarpe-papaya.googlecode.com/git/javadoc/com/googlecode/kevinarpe/papaya/annotation/NotFullyTested.html) or [@FullyTested](http://docs.kevinarpe-papaya.googlecode.com/git/javadoc/com/googlecode/kevinarpe/papaya/annotation/FullyTested.html).  Most code is "fully tested" at first release.  However, code that is released as @NotFullyTested will normally be tested in the next release.

## Documentation ##

All packages, (public and protected) classes, and (public and protected) methods are fully documented with standard Javadoc, including a variety of useful references peppered throughout.  All known exceptions, including unchecked (runtime) exceptions, are fully documented for each method.

[Browse the latest API docs here.](http://docs.kevinarpe-papaya-swing.googlecode.com/git/javadoc/index.html)

## License ##

Unlike many open source Java libraries, this project is not licensed under Apache License 2.0.  Instead, I am using GNU General Public License 3.0+ with a "[Classpath](http://www.gnu.org/software/classpath/license.html)" linking exception.  (More on [Wikipedia](http://en.wikipedia.org/wiki/GPL_linking_exception).)  This exception allows this library to be used unmodified in any project, regardless of license, without affecting the linking project's license.  If the Classpath linking exception were not available, linking projects would be required to use a GPL-compatible license and fully adhere to GPL license requirements under all circumstances.

In short:
  * If your project only links to this library, your project's license **is not** affected.
  * If your project modifies, copies, or borrows source code from this library, your project's license **is** affected, and must be GPL-compatible.

## Maven Central Repository ##

The JARs produced by this project are now released to the [Maven Central Repository](http://search.maven.org/).  ([Example search result](http://search.maven.org/#search|ga|1|kevinarpe-papaya-swing).)  Special thanks is owed to [Sonatype](http://www.sonatype.com/) for donating time and resources to the open source community to make publishing to the Maven Central Repository possible for all.

Release Two is available after 5 Aug 2013.

Apache Maven dependency:
```
<dependency>
  <groupId>com.googlecode.kevinarpe-papaya-swing</groupId>
  <artifactId>kevinarpe-papaya-swing</artifactId>
  <version>0.0.2</version>
</dependency>
```