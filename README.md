<br />
<p align="center">
 <a href="https://github.com/sassansh/YouAnimate">
    <img src="/images/logo.png" alt="Logo" width="80" height="80">
  </a>
  <h2 align="center">YouAnimate</h2>

  <p align="center">
     Design, evaluation and implementation of a new small <a href="https://en.wikipedia.org/wiki/Domain-specific_language">DSL</a> for creating animation intros for Youtube videos.
  <br>
     Built as a group project for UBC <a href="https://courses.students.ubc.ca/cs/courseschedule?pname=subjarea&tname=subj-course&dept=CPSC&course=317">CPSC 410</a> (Advanced Software Engineering).
  </p>
</p>

## Table of Contents

- [Technology Stack 🛠️](#technology-stack-)
- [Prerequisites 🍪](#prerequisites-)
- [DSL Documentation 📑](#dsl-documentation-)
- [Setup 🔧](#setup-)
- [Team ‎😃](#team-)

## Technology Stack 🛠️

- [Java](https://www.java.com/)
- [ANTLR](https://www.antlr.org/)
- [JavaScript](https://www.javascript.com/)
- [Three.js](https://threejs.org/)

## Prerequisites 🍪

You should have [Git](https://git-scm.com/), [Java JDK](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html), and [IntelliJ](https://www.jetbrains.com/idea/) installed on your PC.

## DSL Documentation 📑

You can find the documentation for our DSL [here](https://docs.google.com/document/u/1/d/1JZ_tn1B5y1mpXwdKgLnvXXabwn9wzKqyWTBE4OTIp9s/edit).

## Setup 🔧

1. Clone the repo using:

   ```bash
     git clone https://github.com/sassansh/YouAnimate
   ```

2. To install the SDK:
   1. Download the [Java JDK](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html) (this project has been tested with SDK 16.0.2 - not sure about other versions).
   2. If you download the installer for your specific machine and run it, the Java version should be set up for you.
   3. Select File > Project Structure.
   4. Under 'Project Settings', choose 'Project' and for 'Project SDK' select '16' (it should automatically be detected and selectable).
3. To install ANTLR:
   1. Select File > Project Structure.
   2. Under 'Project Settings', choose 'Libraries' and select the '+' icon to add a new project library.
   3. Choose 'JAVA' and select YouAnimate/lib/antlr-4.9.2-complete.jar.
4. To generate (or update) the lexer files:
   1. Right-click on YouAnimate/src/parser/YouAnimateLexer.g4.
   2. Select 'Generate ANTLR Recognizer.'
   3. The files will show up under gen/parser.
5. To generate (or update) the parser files:
   1. Right-click on YouAnimate/src/parser/YouAnimateParser.g4.
   2. Select 'Generate ANTLR Recognizer.'
   3. The files will show up under gen/parser.
6. To set the project's source folders:
   1. Select File > Project Structure.
   2. Under 'Project Settings', choose 'Modules.' Right-click the gen folder, and select 'Sources.'
   3. Make sure both the 'src' and 'gen' folders are blue and listed underneath the 'Source Folders' header on the right.
7. To build the project:
   1. Select Build > Build Project.
8. To generate your animation
   1. Write your input inside of YouAnimate/input.ttxt
   1. Then run the Main class.
   1. In the console log, a list of the tokens detected will be displayed, along with any errors for the lexer / parser / evaluator.
   1. You will now have a generated HTML file at YouAnimate/index.html
   1. Open YouAnimate/index.html in a browser to view your animation

**NOTE**: If you encounter any errors (ex. with the ANTLR files, class imports, project builds, etc.), try selecting File > Invalidate Caches.
Then select 'Invalidate & Restart.'

## Team ‎😃

Sassan Shokoohi - [GitHub](https://github.com/sassansh) - [LinkedIn](https://www.linkedin.com/in/sassanshokoohi/) - [Personal Website](https://sassanshokoohi.ca)

Cara Wong - [GitHub](https://github.com/cara-wong) - [LinkedIn](https://www.linkedin.com/in/cara-wong/)

Amir Jafarvand - [GitHub](https://github.com/amirjfr) - [LinkedIn](https://www.linkedin.com/in/amir-jafarvand/) - [Personal Website](http://www.amirjafarvand.com/)

Angela Tian - [GitHub](https://github.com/tiangela1027) - [LinkedIn](https://www.linkedin.com/in/tiangela/) - [Personal Website](https://tiangela1027.github.io)

Anna Takeuchi - [GitHub](https://github.com/annatake) - [LinkedIn](https://www.linkedin.com/in/anna-takeu/?originalSubdomain=ca)
