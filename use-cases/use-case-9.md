# USE CASE: 9 Produce a report summarising the number of people who speak the following the following languages (Chinese,English,Hindi,Spanish,Arabic) from the greatest number to smallest, including the percentage of the world population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee I want to produce the following reports:

* the number of people who speak certain languages from greatest number to smallest, including the percentage of the world population:

### Scope

Company.

### Level

Primary task.

### Preconditions

* We know the population of the world
* Database contains current population data
* Database contains spoken language data

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
2. The employee captures the world population and spoken languages
3. Employee extracts number of people speaking certain languages sorted from the greatest to the smallest
4. Employee provides report to management.

## EXTENSIONS

1. **Subset does not exist**:
    1. Employee informs management that some data for the subset does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 2.0