# USE CASE: 7 Produce a report on population of people living in cities, and people not living in cities in each continent, region, country

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee I want to produce the following reports:
* The population of people, people living in cities, and people not living in cities in each continent.
* The population of people, people living in cities, and people not living in cities in each region.
* The population of people, people living in cities, and people not living in cities in each country.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

* We know the population of people living and not living in cities in each continent
* We know the population of people living and not living in cities in each region
* We know the population of people living and not living in cities in each country
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

1. Request population information for a given subset of countries
2. The employee captures the subset (world, the continent, or region) to get population of people living and not living in cities
3. Employee extracts current population of people living and not living in cities of the given subset.
4. Employee provides report to management.

## EXTENSIONS

1. **Subset does not exist**:
    1. Employee informs management that the city does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 2.0