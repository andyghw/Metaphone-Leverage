# Metaphone-Leverage

## When you upload the files, you need to choose the .csv files in "Metaphone-Leverage\src\main\webapp\upload". Because the former files are UTF-8-BOM encoding, which is not supported by JavaCsv library. If you still want to use the former files, it won't show the id column. 

There are two ways to run this project.

## First method
After cloning this project, open your cmd and go inside the root directory of this repository. 

Then use "mvn spring-boot:run" command, it would download all dependencies and compile automatically.

When built successfully, open you browser and open a page with URL "http://localhost:8080/".

## Second method
Import thie project in your Intellij or other IDEs, and you can run this project in your IDE. 

For example, if you use Intellij, just reimport or open this project, and click the "spring-boot:run" plugin.

When built successfully, open you browser and open a page with URL "http://localhost:8080/".
