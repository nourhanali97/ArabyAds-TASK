
# Araby Ads Task 


A brief description of what this project does and who it's for


## clone project

To download this project run on your terminal

```bash
git clone https://github.com/nourhanali97/ArabyAds-TASK.git
```
```bash
git checkout master
```

##RUN APPLICATION STEPS
## Run all test cases

To download this project run on your terminal

```bash
mvn clean install
```

```bash
mvn -Dtest="com.arabyAds.task.**" test

```

## Run Standrad User Test Cases
```bash
mvn test -Dtest=StandardUserScenario 

```


## Run LockedOut User Test Cases
```bash
mvn test -Dtest=LockedOutUserScenario 

```


## Run Performance User Test Cases
```bash
mvn test -Dtest=PerformanceUserScenario 

```


## Run Problem User Test Cases
```bash
mvn test -Dtest=ProblemUserScenario 

```
