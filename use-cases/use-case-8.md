# USE CASE: 8 Produce a report summarising the population by each category: world, continent, region, country, district, city

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee I want to produce the following reports:

* The population of the world. 
* The population of a continent. 
* The population of a region. 
* The population of a country. 
* The population of a district. 
* The population of a city.

### Scope

Company.

### Level

Primary task.

### Preconditions

* We know the population of the world
* We know the population of a continent
* We know the population of a region
* We know the population of a country
* We know the population of a district
* We know the population of a city
* Database contains current population data.

### Success End Condition

A report is available for employees to provide when requested by management.

### Failed End Condition

No report is produced.

### Primary Actor

Management.

### Trigger

A request for a report is sent to an employee.

## MAIN SUCCESS SCENARIO

1. Request population information
2. The employee captures the subsets (world, the continent, region, country, district, city) to get population of people living there
3. Employee extracts current population of people for the particular subset.
4. Employee provides report to management.

## EXTENSIONS

1. **Subset does not exist**:
    1. Employee informs management that some data for the subset does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 2.0