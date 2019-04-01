# Ghost Game

<p>Ghost is a written word game in which players take turns to add letters to a english word (from left to right). The first player that completes a valid word longer than 3 characters or introduce a letter that make the current fragment not extendable as a valid word lost. 
Write a web app that play intelligently agains an human user using the english dictionary provided. Let the human play always first. 
The exercise will be evaluated considering the strategy used by the computer to win the game, clarity and legibility of code, architecture of the solution, usage of industry standard frameworks and test coverage.
The aim of the exercise is to present the best skills of the candidate, so, a complex solution is preferred to a minimalistic approach.</p> 


https://en.wikipedia.org/wiki/Ghost_(game)

<h2>Build with:</h2>
<ul>
<li>Maven Multimodule</li>
<li>Spring-boot</li>
<li>H2 (embedded)</li>
<li>jUnit</li>
<li>Angular 5</li>
<li>Jasmine</li>
<li>Karma</li>
</ul>

<h2>Run project</h2>

<h3>Option 1</h3>
Build a project and deploy in Tomcat 8
<ul>
<li>Go to root dir project</li>
<li>Execute 'mvn clean install'</li>
<li>Copy ROOT.war in your Tomcat/webapp/</li>
<li>Open a browser and go to http://localhost:8080/</li>
<li>Enjoy the game!</li>
</ul>

<h3>Option 2</h3>
Build and run projects separately 

<ul>
<li>Go to root dir project</li>
<li>Execute 'mvn -f backend/pom.xml spring-boot:run'</li>
<li>Go to root frontend dir</li>
<li>Execute 'ng serve'</li>
<li>Open a browser and go to http://localhost:4200/</li>
<li>Enjoy the game!</li>
</ul>




