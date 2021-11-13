# Milestones

Use this file to commit information clearly documenting your milestones'
content. If you want to store more information/details besides what's required
for the milestones that's fine too. Make sure that your TA has had a chance to
sign off on your milestones each week (before the deadline); typically you
should discuss your material with them before finalizing it here.

## Table of Contents

- [Milestone 1](#milestone-1)
- [Milestone 2](#milestone-2)
- [Milestone 3](#milestone-3)
- [Milestone 4](#milestone-4)
- [Milestone 5](#milestone-5)

## Milestone 1

### DSL Proposal ✨:

**Animated Youtube Intro Generator (YouAnimate) 🎨**

Our DSL is a language for YouTube creators to create an animated intro clip for their videos. Many YouTubers pay large amounts of money to animators to make them an intro. YouAnimate would be a natural-language based language for creators to create their own intros. YouAnimate will support various shapes and animations and a user will be able to specify a video length. Shapes can be nested within shapes, and animations can be applied to these shapes along the timeline the user specifies. YouAnimate will also support text in the videos. Supported animations will include movement, rotation, size changes and more. Animations can be placed along the timeline using start and end timestamps. The screen of the video will be segmented along both the x and y axis. The points of intersection will be the only points on the screen to simplify where you can move items to on the screen. YouAnimate will allow users to easily create animated videos with no coding experience while maintaining creativity and flexibility over their content.

### Feedback from TA 👨🏻‍🏫:

- Language features should be non trivial so that it allows users to be creative
- We don't want users to have to copy and paste code to replicate behaviour or to create custom shapes every time they want to use it
  - He suggested including the ability to write functions and use control flow (if statements). Or we could build some pre-made functionality that we anticipate will be common animations or flows in our program if we don't want to assume our users will understand functions/control flow. Or we could make it a requirement that our users know the very basics of these.
  - Ex. of a built in pattern: Could have a function that moves a shape in a rectangle pattern (do rect pattern). This way they don't have to manually move the shape to the points that form a rectangle every time they want this pattern.
- TA said this was a good example of an existing language that may have similar syntax to ours because it does something similar → logo
  - [Logo](https://el.media.mit.edu/logo-foundation/what_is_logo/logo_programming.html) has a pretty simple syntax and the user is still able to define functions and have looping/repeating functionality
- We should look into what backend/libraries we are using for the actual animations and video creation. We already decided on three.js and maybe gsap. May want to look into this more to build out our options and also look into video creating libraries.
- TA suggested adding some language feature/control flow examples.
  - There should be enough features so that people can be creative and approach a single problem in two different ways
  - Needs to have some type of looping functionality that allows people to save keystrokes to not repeat something all the time

### Changes based on TA Feedback 🛠:

**Language Features:**

- Users can define their own shape outside of what the language provides (ex. Language has circles, squares, triangles, but user wants to define a star)
  - We will need to figure out how a user can define their own shapes (maybe they can specify points and our program would just connect them with straight lines to make it simple)
- Users can define a function/procedure and use it within other procedures
- Users can define a set/grouping of shapes so they would not need to apply animations to every single shape, but to the entire group instead
- We will explore the possibility of including control flow with if statements, however the main way of controlling the animations is through the timeline (use of timestamps) to start and stop specific animations in the video.
- Users can implement looping functionality for defined animations using 'x 5' to repeat the effect 5 times from beginning to end or 'x infinite' to loop the animation to the end of the video.

**Backend Tech Stack Ideas:**

- JavaScript Animation libraries
  - [three.js ](https://threejs.org/)(best suited for 3D animations
  - [p5.js ](https://p5js.org/)(best suited for 2D animations)
- Convert to video format:
  - [ccapture.js](https://github.com/spite/ccapture.js/)
- Articles:
  - Stackoverflow discussion on converting webgl animation to video format ([Link](https://stackoverflow.com/questions/31499717/best-way-to-record-a-html-canvas-webgl-animation-server-side-into-a-video))
  - Converting three.js to video using ccapture.js ([Link](https://janakiev.com/blog/videos-and-gifs-with-threejs/))
  - Converting p5.js to webm using ccapture.js ([Link](https://stackoverflow.com/questions/42437971/exporting-a-video-in-p5-js/53182156#53182156)) ([Demo](https://glitch.com/~ccapture-p5-demo))

## Milestone 2

### Division of Main Responsibilities 💼

Our whole team will be helping with every aspect of the project, but we have assigned owners/leaders for each category of the project. These owners are responsible for ensuring their assigned category is on track to be completed by the set deadline.

- Sassan
  - Repo management / Submissions
  - User Studies (w/ Cara)
  - Edit Final Video
- Cara
  - User studies (w/ Sassan)
  - Skeleton setup (w/ Angela)
  - Communication with TA
- Amir
  - Language Implementation
- Angela
  - Skeleton setup (w/ Cara)
- Anna
  - Language/Grammar Design
  - Mapping language to Three.js

### Roadmap 🛣

The following are approximate deadlines and tasks set by our team. These are soft deadlines set ahead of milestone deadlines in order to keep our team on track and ensure there is enough time for implementation and debugging.

- Milestone 2: Sept. 24
- First User Study: Sep. 28
- Language Design + Project Validation (technical components ie. create skeleton): Sept. 30
- Writing g4 files and generating Java files with ANTLR: Oct. 1
- Milestone 3: Oct. 1
- Final user study plan: Oct. 8
- Milestone 4: Oct. 8
- Implementation: Oct. 10
  - Java ANTLR
  - Three.js
  - ccapture.js
  - Generate a video file
- Create Final Video: Oct. 15
- Milestone 5: Oct. 15
- Final Project Submission: Oct. 18

### Draft Grammer with Example 📝

#### Language Features

- Ability to set screen width, height and color
- Ability to create preset shapes (`circle`, `oval`, `square`, `rectangle`, `triangle`, `star`, `text`, `sphere`, `cube`)
  - Ability to set size and colour for each shape
- Ability to choose from preset animations and apply these to shapes that have been created. Animations that we will support are below (all animations will also have start and stop timestamp)
  - `move` with start and end positions (would move in straight line)
  - `rotate` with a parameter for counterclockwise or clockwise and a parameter for how many rotations
  - `grow` with a parameter that represents the multiplier to grow the dimensions of the shape
  - `shrink` with a parameter that represents the multiplier to shrink the dimensions of the shape
  - `stay` with coordinates to stay at and start and end times
    - **TA Feedback:** TA said we don't need stay because it is intuitive that the object would stay on the screen.
      - Response: However, with this approach there would be no way to remove a shape from the screen. We would like this feature as we believe it is a valuable feature for flexibility to the user. Therefore, we have opted to keep this command and will verify the need for this with our user studies.
- Ability to group shapes and apply animations to groups (shapes within groups are layered on top of each other based on the order they are grouped in)
- Ability to create functions to define animation behaviour
- Ability to define for loops that work similar to java for loops but with a more understandable syntax
  - **TA Feedback:** Originally we had a "repeat" function, but the TA suggested a for loop for easier implementation and flexibility to the user. We chose to go with this suggestion.

#### Stretch Goal Features

- Ability for the user to create custom shapes (This won't be too hard to implement, but may be hard for the user to use which won't provide great value, it's better to provide more preset shapes)
- Conditional execution
- Ability to set duration of video
- **TA Feedback:** consider grouping as an array
  - Response: As discussed with the TA, this is a good stretch goal and we will focus on functions and looping first. This approach would also require groups to have their own coordinate system (like a canvas within canvas).

#### Example 1

Creates a logo which is a red circle overlaid by text (“CPSC 410) on a green background and groups these. Creates a function to move a shape across the screen and back. Calls this function with the created group and calls this function three times.

<img src="/images/example1.gif" alt="Example 1" width="350">

```
canvas size: 1920 by 1080
canvas color: green
logo_shape is red circle size 10
logo_text is white “CPSC 410” font-size 12
group G1 is logo_text on logo_shape

animation-def: across-screen with input (obj, start) is:
    move obj from (100,100) to (1800,100) from start to start+3
    stay obj at (1800,100) from start+3 to start+4
    move obj from (1800,100) to (100,100) from start+4 to start+7
    stay obj at (100,100) from start+7 to start+8

for start from 0 to 16 skip 8:
    across-screen(G1, start)
```

#### Draft Grammer

<pre>
<b>program:</b> canvas-size canvas-color statements*;
<b>canvas-size:</b> ‘canvas size:’ NUM ‘by’ NUM;
<b>canvas-color:</b> ‘canvas color:’ COLOR;
<b>statements:</b> shape-dec | text-dec | group-dec | animation-def | loop;
<b>shape-dec:</b> TEXT ‘is’ COLOR SHAPE ‘size’ (NUM)+;
<b>group-dec:</b> ‘group’ TEXT ‘is’ TEXT (‘on’ TEXT)+;
<b>text-dec:</b> TEXT ‘is’ COLOR TEXT ‘font-size’ NUM;

// other animation commands will be similar to the below
<b>anim-statement:</b> move | stay
<b>move:</b> ‘move’ TEXT ‘from’ XY ‘to’ XY ‘from’ timestamp ‘to’ timestamp;
<b>stay:</b> ‘stay’ TEXT ‘at’ XY ‘to’ XY ‘from’;

<b>animation-def:</b> ‘animation-def:’ TEXT ‘with input (obj, start) is :’ NL (func-statement)+;
<b>func-statement:</b> TAB  anim-statement;
<b>loop:</b> ‘for start from‘ NUM ‘to’ NUM ‘skip’ NUM ‘:’ NL loop-statement+;
<b>loop-statement:</b> func-call | anim-statement;
<b>func-call:</b> TAB TEXT ‘(‘ INPUT ‘)’;
<b>timestamp:</b> (NUM | ‘start’ | PLUS);
<b>PLUS:</b> ‘start +’ NUM
<b>SHAPE:</b> ‘circle’ | ‘triangle’ | ‘square’ | ‘rectangle’ | ‘sphere’ | ‘cube’ | ‘pyramid’;
<b>COLOR:</b> ‘red’ | ‘green’ | ‘yellow’ | ‘blue’ | ‘orange’ | ‘purple’ | ‘white’ | ‘black’;
XY: ‘(‘ NUM ‘,’ NUM ‘)’ 
<b>TEXT:</b>  [a-zA-Z1-9]+;
<b>NUM:</b> [0-9]+;
<b>NL:</b> [\n];
<b>TAB:</b> [\t];
</pre>

### Summary of Progress So Far 📍

Our team has completed the following so far:

- Brainstormed and finalized our project idea
- Divided high-level responsibilities among the team
- Created a rough project timeline with our own deadlines that we would like to follow
- Designed the language features we want our language to support and stretch goal features
- Created a sample program from our language
- Looked into adobe flash and action scripts as inspiration
  - **TA Feedback:** This was motivated by our TA's suggestion
- Created our first draft grammar

## Milestone 3

### Mockup of Concrete Language Design 💻:

The initial implementation for the Lexer and Parser can be found in the repo:

- [YouAnimateLexer.g4](https://github.students.cs.ubc.ca/cpsc410-2021w-t1/Project1Group2/blob/6f7860b9f0bb343b74053069f30aba9b2226736a/YouAnimate/src/parser/YouAnimateLexer.g4)
- [YouAnimateParser.g4](https://github.students.cs.ubc.ca/cpsc410-2021w-t1/Project1Group2/blob/6f7860b9f0bb343b74053069f30aba9b2226736a/YouAnimate/src/parser/YouAnimateParser.g4)

### User Studies 🔍:

#### How we ran our first user studies:

- Participants were first given a background about our language, it's purpose, and the benefits we hope it will provide.
- We then showed participants an example task and a code snippet of how someone would complete the task in YouAnimate. We went through how the code worked, and they had a chance to ask any questions they had.
- We then gave the participant a similar task to complete on their own. We gave more details in some parts of the task and left out details in other parts to gauge how the user understood the language. The task we assigned is below:
  - Create a video with a canvas of width 1500 px, a length of 2100px, and a background color of blue. Create a purple rectangle with length 100px and width 30px, overlayed with pink text of font size 10 that reads "I'm a YouAnimate wizard!". Create a function that moves the rectangle and text up and down vertically. Have it stay at the top and bottom for a second, and have it move up for 2 seconds, and down for 2 seconds. Repeat this animation ten times. While you're working, please "think aloud". Say what your thought process is, what's confusing, what makes sense, why you're doing something. etc.
- After the participant had attempted to complete the task, we went through the code they wrote and collected feedback from them on their experience with the language, what they found intuitive or challenging, and any suggestions they had.

#### User Study #1 Notes:

- Student Background:
  - 4th year UBC BSC student
  - Youtube content creator for 4 years
  - 3.51K subscribers
- Study notes:
  - The participant really liked the idea of the DSL - she said it filled a very serious need as it's hard to make professional looking intros with creativity without someone who is experienced in making animations.
  - The participant was able to just look at the example code with minimal explanation from us and understand what the code was doing, with the exception of the for loop. She clarified what the numbers signified in the for loop header. It was clear she was able to understand most of the code just by looking at an example, because of her computer science background.
  - While she was completing the task she was given she didn't ask any questions.
  - Here was what she got wrong/was different from the expected answer (everything not mentioned was done correctly):
    - Mixed up order of height and width parameters when setting the canvas size and creating the rectangle.
    - Unfortunately she didn't get any of the time frames correct (up for 2 seconds, stay for 1 second, repeat 10 times etc.). She said she missed this part because she was making sense of the example code, but she said the answer key for this part of the task made sense.
  - Here was her feedback after completing the task:
    - In general, she said the language was very intuitive, because it follows a lot of the same concepts of commonly used programming languages.
    - Suggested to label parameters for shape creation and setting the canvas size like "length=x height=y"
    - The for loop header was not as intuitive as everything else as it required an explanation.
    - ​​She doesn't think a non computer science student would have an easy time using the language without a lot of explanation or document reading (specially about the function and for loop concepts).

#### User Study #2 Notes:

- Student Background:
  - Education: CapU Bachelor of Business, 3rd year
  - Any animation/video experience: Never done anything like this before
  - Any computer science/coding: No coding experience
- Study notes:
  - Initial impressions of the DSL:
    - The user finds the DSL very useful for beginner youtubers to create their intro animations.
  - Thoughts on the completed example:
    - After going through the example, the user did not have any questions. However, they did express that they were quite confused by most of it.
  - Questions and thoughts while working on the given task:
    - While defining the rectangle, the user was confused by how to indicate the rectangle's size when you have 2 dimensions instead of 1 in the example.
    - In the function: the user was very confused about how to define the coordinates for the animations
    - The timestamps of "start+" really confused the user as well.
    - User prefers just writing how long the animation should last rather than start and end times
    - Confused by how to define the loop times, they gave up and continued.
  - Compare to expected answer and take notes:
    - Canvas width and height were flipped. User prefers writing "width XXX and length XXX"
    - Rectangle width and height were separated with "by" as in the canvas size. User prefers their way.
    - The coordinates were incorrect in the move call. User prefers a split screen with numbered sections to use.
    - Timestamps confused the user, rather just say how long for the animation to last "for 2 seconds"
    - Loop definition had incorrect start and to timestamps and skip. They prefer just saying repeat this 10 times.
  - Wrap up thoughts, experience, how can we improve:
    - Alright in general, but quite confusing. Be more explicit with what we should put where.
    - User took a lot of inspiration from the example code given and was only able to get the parts that were not drastically changed correct. In a future study, the example shown and task asked to be completed should differ more or the example code should not be accessible to the user as they are completing the task.

### Changes to Original Language Design ✨:

The changes we made from our user studies are documented below. The following example reflects these changes.

#### Original:

```
canvas size: 1920 by 1080
canvas color: green
logo_shape is red circle size 10
logo_text is white “CPSC 410” font-size 12
group G1 is logo_text on logo_shape

animation-def: across-screen with input (obj, start) is:
    move obj from (100,100) to (1800,100) from start to start+3
    stay obj at (1800,100) from start+3 to start+4
    move obj from (1800,100) to (100,100) from start+4 to start+7
    stay obj at (100,100) from start+7 to start+8

for start from 0 to 16 skip 8:
    across-screen(G1, start)
```

#### Changes after User Study :

- Labeled parameters and getting rid of "by": Both users had trouble with the order of the length and height parameters while setting the canvas side and in creating a rectangle. Therefore, we decided to label these parameters so it is clear and this way our language will also be able to handle the case where the user switches the order as long as they are labeled. As a result of this change, we also got rid of the "by" between the length and height parameters.
- Simplify wording for specifying the time stamps of animation commands: Our previous syntax required you to specify the start and end time of animation commands. This was hard for non-CS students to understand. We changed this to have more natural language wording to make this more intuitive.
- Getting rid of second parameter for animation functions: From our non-CS user study, the user had trouble with the start parameter we previously had which was used to specify timestamps in the animations (ie. start+3, start+4 etc.). We have changed it so that the start and end times in functions are hard coded such that a function has its own timeline. When the function is called, the user will "place" it along the main timeline with a start value. This means when animation commands are used outside of functions, the timestamps used will be placing the animation along the main timeline.
- Simplification of loop and its syntax: In both of our user studies (CS and non-CS), users had a hard time understanding our previous loop. We've changed the loop to have more natural language wording where the user says "repeat", the name of the function, the number of times they want to repeat it, and the start time of where they want to place the behaviour on the main timeline. This creates some limitations from our previous version but we opted for this decision in favor of user experience and usability.
- "Run" → "Play": more intuitive wording

```
canvas size: length=1920 height=1080
canvas color: green
shape logo_shape: red circle radius=10
text logo_text: white "CPSC 410" font-size 12
group G1 is logo_text on logo_shape
shape logo_shape2: blue rectangle height=5 width=10
text logo_text2: black "CPSC 410" font-size 15
group G2 is logo_text2 on logo_shape2

animation-def: across_screen with input (obj):
  move obj from [100|100] to [1800|100] starting at 0s for 3s
	stay obj at [1800|100] starting at 3s for 1s
	move obj from [1800|100] to [100|100] starting at 4s for 3s
	stay obj at [100|100] starting at 7s for 1s

repeat across_screen(G1) 3 times starting at 0s
play across_screen(G2, 0) start at 10s
move logo_shape from [0|50] to [0|100] starting at 15s for 2s
```

#### Changes after TA Feedback:

- Introduce back the ability to add a parameter: We had previously removed the ability of adding additional parameters to our functions as non-cs users were confused by this, but the TA pointed out that this was too limiting for the language. We added it back, however, our functions will still have it's own time frame, so that these values must be hardcoded. (ie. you can only use the parameter to edit the coordinates).
- Adding back original loop construct but with more natural language wording: Again, introduced back the looping, as it was too limiting to remove it. We weren't able to do parameters, nesting loops etc. with what he had before. However, we changed the wording so it's more natural language based. You can now pass in a parameter to a function and use i to specify the start time of the function calls.
- Keep the easier version of the "loop": Even though we added back a lot of the concepts we removed due to the TA feedback, we are also still keeping our easier version of the loop (the repeat command). This gives the user the option to use both, and if they want more flexibility in looping behaviour they can use the actual loop rather than the repeat command.

```
canvas size: length=1920 height=1080
canvas color: green
shape logo_shape: red circle radius=10
text logo_text: white "CPSC 410" font-size 12
group G1 is logo_text on logo_shape
shape logo_shape2: blue rectangle height=5 width=10
text logo_text2: black "CPSC 410" font-size 15
group G2 is logo_text2 on logo_shape2

animation-def: across_screen with input (obj, n):
	move obj from [100|100+n] to [1800|100+n] starting at 0s for 3s
	stay obj at [1800|100+n] starting at 3s for 1s
	move obj from [1800|100+n] to [100|100+n] starting at 4s for 3s
	stay obj at [100|100+n] starting at 7s for 1s

loop i from [0] to [16] increment 8:
	play across_screen(G1, i*10) at is

repeat across_screen(G1, 0) 3 times starting at 0s
play across_screen(G2, 0) start at 10s
move logo_shape from [0|50] to [0|100] starting at 15s for 2s
```

## Milestone 4

### Status of Implementation 🔧:

In the last week, we delegated tasks based on three main areas of our implementation - updates to our lexer/parser based on TA and user study feedback, implementation of the visitor pattern, and implementations and placeholders for the commands/features in our language. The latter category involved creating three.js code to render our shapes/text onto the screen with the dimensions passed in by the user with appropriate error handling. Our other language features require variable memory/storage to be implemented so we implemented the placeholder code that set-up the infrastructure of these features so it's ready to go when variable storage is implemented.

- Updated lexer / parser based on TA and user study feedback
- Implemented visitor pattern
- Implemented basic commands and features of our language
  - Create Three.js scene and camera placement
  - Create basic shapes and text with support for size and colour
  - Started implementation of animations, loops, and functions
- Implemented some error handling
- Created plan for next steps of implementation

### Plans for Final User Study 🔍:

- Identified 2 new candidates for our final user study
  - In the first user study, the users were either too experienced in computer science making the task easier or no experience at all making them struggle a lot.
  - In the final user study, one of the candidates will have very minor computer science experience and the other will be experienced in video editing to get a better sense of how the average user would perform with our language.
- Plan to create some basic documentation with examples to make available to our users while they complete the given task.
- In the first user study, we identified that the given task and shown example were too similar. The user was able to mostly copy from the given example with minor changes to complete the task
  - We plan to make the given task vary more from the given examples to truly test the usability of the language.

### Planned Timeline for The Remaining Days ⌛:

- We are aiming to get our implementation done by end of day Oct. 13 (leaves some buffer room). This includes implementation of:
  - Variable/memory storage
  - Layering and grouping of elements
  - Animation commands in three.js
  - Implementation of functions
  - Implementation of the two types of loops in our language
- We are aiming to get our final user studies done by end of day Oct. 14
- We are aiming to get our testing, final reviews, and clean-up of our implementation done by end of day Oct. 15. This step includes:
  - Testing for expected behaviour in our language
  - Testing against edge cases to ensure proper error handling
  - Buffer room to finish any outstanding implementation or integration issues for parts of the code
- We are aiming to get our video done by end of day Oct. 17

## Milestone 5

### Status of Implementation 🔧:

Starting at the beginning of the milestone, all the features were split evenly across the members of the team. A process for managing the git and merging into master was created where we would branch off from master, implement a feature and only after 2 approvals on the pull request would we merge into master. We were able to quickly complete all of our requirements. Our language supports all of the features of creating texts, shapes, groups and animating them through the use of stay, move and rotate commands. We even went as far as implementing some control flow through the use of loops and function definitions.

- Lexer and parser are very mature and stable at this point
- All standard requirements are met and features are complete
  - Thoroughly tested by team members on each pull request
  - Completed features include:
    - Background colour customization
    - Text and shape declarations
    - Grouping of text and shapes
    - Move, stay and rotate animations for all text, shapes and groups
    - Animation definition feature where user can encapsulate several animations and call them later within any object and coordinate variables
    - Function Call feature to call the animation definitions previously declared
    - Loop feature through the use of "repeat x times" or loop x from [x] to [y] increment z"
- Some basic error handling
  - Runtime errors are thrown when:
    - Shape or text declarations use the same name more than once
    - Animation definitions use the same name more than once
    - If any shape or text (even if in a group) is being asked to animate more than once in a given second
- Minor changes to our implementation are pending from the results of our final user studies (covered in our planned timeline below)

### Status/Results of Final User Study 🔍:

We ran our final user studies after our base implementation was complete. This allowed our users to write their code and test it as they went. We followed the same process as our original studies but applied our learnings from the first user studies (made our task less similar than the example code snippets we gave users). We ran our final user studies on two 2nd year students. The profiles for each student is below:

- Student #1: 2nd Year Arts student, no prior coding experience, experience in creating video content
- Student #2: 2nd Year Arts student, prior coding experience (CPSC 103), no experience in creating video content

The feedback we got from these users were very similar and is outlined below:

- Our text elements should allow for special characters (specifically exclamation marks, apostrophes, and @ symbols)
- The word "color" used in our language should be spelled as "colour" as this is Canadian so it is more intuitive
- The colors and fonts used in our animations could be "nicer" (suggestions were using pastel colors and a less standard font type)
- The way we use our variables in our animation statements inside of functions and for loops is not super intuitive (in functions you use the parameters to alter the coordinates and in loops we use the variable to alter the time frame)

General comments and notes from our studies:

- We found the student with CS experience preferred to refer to the documentation we prepared on our language rather than the code snippets when completing the task, while the non-CS student preferred the code snippets.
- If there were any problems with the code itself (failed when running the code), the CS student liked having the stack trace to find the error, while the stack trace was overwhelming to the non-CS user.

### Planned Timeline for The Remaining Days ⌛:

- We plan to accommodate minor changes to implementation, based on the results from the user study. These we will consider and finalize prior to submission.
  - Deadline: EOD Oct. 15
  - Such changes could potentially include the following:
    - Making runtime errors more clear and verbose
    - Modifying the loop functionality to better suit the desired capability of repeating animations (ex. allowing variables to modify the coordinates of elements)
    - Fixing or expanding small aspects of our language (i.e. supporting special characters in text elements, adapting better to user's notion of language)
    - Other small changes to the general graphics themselves, such as editing the colours and fonts
- Create a 3-minute video that captures the overall functionality and results of our DSL, as well as the user study feedback, with respect to the task of generating 2D animations via our language
  - Deadline: EOD Oct. 18
    - We plan to complete the slides, videos, and audio for the video by EOD Oct. 16
    - We will finish the video by EOD Oct. 17
  - Tasks we'd need to consider would be:
    - Creating rough draft outline of the video, in regard to how to structure the demonstration of our process and results
    - Creating our very own intro using our DSL
    - Filming the video, via screen capture and voiceover
    - Editing the video
