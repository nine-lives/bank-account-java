# Verify a UK Bank Account - Utils and Modulus Validation Java SDK

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.9ls/bank-account/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.9ls/bank-account)
[![Build Status](https://api.travis-ci.com/nine-lives/bank-account-java.png)](https://app.travis-ci.com/nine-lives/bank-account-java)
[![Code Quality](https://api.codacy.com/project/badge/grade/b567861b416a4c01b782bb8754ec48f8)](https://www.codacy.com/app/nine-lives/bank-account-java)
[![Coverage](https://api.codacy.com/project/badge/coverage/b567861b416a4c01b782bb8754ec48f8)](https://www.codacy.com/app/nine-lives/bank-account-java)
[![Javadocs](https://www.javadoc.io/badge/com.9ls/bank-account.svg)](https://www.javadoc.io/doc/com.9ls/bank-account)

## Getting Started

This SDK allows you to validate sort code and account number combinations pass the modulus checking.   

The sdk is hosted on maven central so you can include it as a dependency in your projects as follows:

### Gradle/Grails

```
    compile 'com.9ls:bank-account:1.6.8'
```

### Apache Maven

```
    <dependency>
        <groupId>com.9ls</groupId>
        <artifactId>bank-account</artifactId>
        <version>1.6.8</version>
    </dependency>
```

### Apache Ivy
```
    <dependency org="com.9ls" name="bank-account" rev="1.6.8" />
```

## Validation and Modulus Checking

To check a bank account:

```java
        BankAccountValidator validator = new BankAccountValidator();
        boolean valid = validator.valid('08-60-90', '06774744');
```

## SortCode Entity

You can use the SortCode entity to represent a sort code:

```java 
    SortCode sc = new SortCode("12-34-56");
    sc.toString().equals("12-34-56");  
    sc.toString("").equals("123456");  
    sc.toString(" ").equals("12 34 56");
```

You don't need to have the hyphens:    

```java 
    SortCode sc = new SortCode("123456");
    sc.toString().equals("12-34-56");
```      
   
Or you can construct it with an integer
      
```java 
    SortCode sc = new SortCode(123456);
    sc.toString().equals("12-34-56");  
```

## BankAccount Entity

You can use the BankAccount entity to represent a sort code and account number tuple:

```java 
    BankAccount account = new BankAccount('08-60-90', '06774744');
    account.getSortCode().toString().equals('08-60-90');
    account.getAccountNumber().toString().equals('06774744');
```
 
## Validation and Substitution Tables

The SDK uses the latest valacdos.txt and scsubtab.txt from 
[Vocalink](https://www.vocalink.com/tools/modulus-checking/).
If you need to use alternate file you can initialise the BankAccountValidator 
with different files. 

```java
    BankAccountValidator validator = new BankAccountValidator(
            ModulusTable.load(new FileInputStream("/home/my-valacdos.txt")),
            SubstitutionTable.load(new FileInputStream("/home/my-scsubtab.txt")),
            ModulusValidationHandlerProvider.getInstance());
```

| Name | File | Version | SDK Version |
| ---- | ---- | ------ | --- |
| Sorting Code Substitution Data | scsubtab.txt | Valid from 13 June 2005 | 1.0.0+ |
| Modulus Weight Table Data | valacdos.txt | Valid from 27 May 2019 | 1.0.0+ |
| Modulus Weight Table Data | valacdos.txt | Valid from 29 September 2019 | 1.0.3 |
| Modulus Weight Table Data | valacdos.txt | Valid from 6 April 2020 | 1.0.4 |
| Modulus Weight Table Data | valacdos.txt | Valid from 7 April 2020 | 1.6.1 |
| Modulus Weight Table Data | valacdos.txt | Valid from 14 September 2020 | 1.6.4 |
| Modulus Weight Table Data | valacdos.txt | Valid from 20 September 2021 | 1.6.7 |
| Modulus Weight Table Data | valacdos.txt | Valid from 13 June 2022 | 1.6.8 |

## Build

Once you have checked out the project you can build and test the project with the following command:

```
    gradlew check jar
```

 