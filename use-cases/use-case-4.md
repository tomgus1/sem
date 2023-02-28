# USE CASE: 4 Produce a report on population size, for the top N cities, for a subset of cities

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee I want to produce the following reports:
* The top N populated cities in the world where N is provided by the user. 
* The top N populated cities in a continent where N is provided by the user. 
* The top N populated cities in a region where N is provided by the user. 
* The top N populated cities in a country where N is provided by the user. 
* The top N populated cities in a district where N is provided by the user.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

* We know the cities in the world.
* We know the cities in a continent.
* We know the cities in a region. [Region to be defined]
* We know the cities in a country.
* We know the cities in a district.
* We know 'N'.
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

1. Request population information for a given subset of cities, limit N
2. The employee captures the subset (world, the continent, region, country, or district) to get population information for.
3. Employee extracts current population of cities of the given subset with limit N.
4. Employee provides report to management.

## EXTENSIONS

1. **Subset does not exist**:
    1. Employee informs management that the continent, region, country or district does not exist.

## SUB-VARIATIONS

If 'N' is larger than the list of cities in the subset, then the query shouldn't fail, it should just return the full list.

## SCHEDULE

**DUE DATE**: Release 2.0