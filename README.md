# Fractional Calculus Computation API

A Java-based API for computing Caputo and Riemann-Liouville fractional derivatives of user-provided polynomial
expressions. 

## Table of Contents
1.) [Project Status](#project-status)  
2.) [Technologies Used](#technologies-used)  
3.) [Getting Started](#getting-started)  
4.) [Installation](#installation)  
5.) [Usage](#usage)  
6.) [API Reference](#api-reference)  
7.) [Examples](#examples)
8.) [Example Calculation](#example-calculation)
9.) [FAQ](#faq)  
10.) [Contributing](#contributing)  
11.) [Changelog](#changelog)  
12.) [Known Issues](#known-issues)  
13.) [License](#license)  
14.) [Acknowledgements](#acknowledgements)  
15.) [Support](#support)  
16.) [Contact Information](#contact-information)

## Project Status

This project is in active development with a current version of 1.0.0. Upcoming features include:  

- Support for fractional integrals
  

- Performance optimizations
    
## Technologies Used

- Java 11 (or higher)
- Maven

  
## Getting Started

### Prerequisites

1.) Clone the repository:  
```sh
git clone https://github.com/trbaxter/fractional-computation-api.git
```

2.) Navigate to the project directory: 
```sh
cd fractional-computation-api
```

3.) Build the project using Maven:
```sh
mvn clean install
```
  

## Usage

### Application Start

To start the application, use the following command:
```sh
mvn spring-boot:run
```

## API Reference

### Caputo Fractional Derivative Endpoint  

Endpoint URL: 
```
/calculate/derivative/caputo
```

Method: <b>POST</b>  

Required request body:
```
{
  "coefficients": [],
  "order": 
}
```
Parameters:

```coefficients``` - An array of polynomial coefficients of type double.

```order``` - The derivative order of type double.

Response:
```
{
    "expression": 
}
```
Returns the closed-form expression of the Caputo derivative if successful.

## Examples

### Example 1: Caputo Derivative with Non-Zero Integer Coefficients

Calculate the closed-form 0.35th Caputo derivative of $3x^2 + 2x + 1$.

Input: 

```
{
  "coefficients": [3,2,1],
  "order": 0.35
}
```

Output: 
```
{
  "expression": "4.040x^1.650 + 2.222x^0.650"
}
```

### Example 2: Caputo Derivative with Zero and Non-Zero Long-Type Coefficients

Calculate the closed-form 1.23456th Caputo derivative of $14.6x^3 + 16.049x - 12$.

Input:
```
{
  "coefficients": [14.6, 0, 16.049, -12],
  "order": 1.23456
}
```

Output:
```
{
    "expression": "53.778x^1.766"
}
```

## Example Calculation

Given the polynomial $f(x) = 3x^2 + 2x + 1$, and the order $\alpha = 0.5$, the Caputo fractional derivative 
$\dfrac{C}{} D \dfrac{0.5}{} f(x)$ is computed as follows: 




## High-level Design Diagram

```mermaid



flowchart TB

%% Sim Start %%
    A([Start]):::start
%% Sim End %% 
    Z([Finish]):::finish
%% Sim Actions %%
    AA[Parse\n &nbspUser Input &nbsp]:::action
    AAA[&nbsp Determine &nbsp\nResult]:::action
%% Sim Decisions %%
    B{Derivative\nor\nIntegral?}:::decision
    E{Type\nof\nExpression?}:::decision
%% Input %%
    C1[/&nbsp Derivative &nbsp/]:::input
    C2[/&nbsp Integral &nbsp/]:::input
    F1[/&nbsp Constant &nbsp/]:::input
    F2[/&nbsp Power &nbsp/]:::input
    F3[/&nbsp Trigonometric &nbsp/]:::input
    F4[/&nbsp Logarithmic &nbsp/]:::input
    F5[/&nbsp Exponential &nbsp/]:::input
    F6[/&nbsp Inverse\nTrigonometric &nbsp/]:::input
%% Output %%
    I1[\&nbsp Display &nbsp &nbsp\n &nbsp Result &nbsp\]:::output
    I2[\&nbsp Result is &nbsp&nbsp&nbsp&nbsp\n &nbsp &nbsp always 0 &nbsp\]:::output
%% Links %%
    A --> B
    B --- C1 & C2
    C1 & C2 --> E
    E --- F1 & F2 & F3 & F4 & F5 & F6
    F1 --> I2
    F2 & F3 & F4 & F5 & F6 --> AA
    AA --> AAA
    AAA --> I1
    I1 --> Z
    I2 --> Z
%% Class Colors %%
    classDef start stroke: #0f0, stroke-width: 2.5px;
    classDef finish stroke: #f00, stroke-width: 2.5px;
    classDef decision stroke: #cc5500, stroke-width: 2.5px;
    classDef action stroke: #196de3, stroke-width: 2.5px;
    classDef input stroke: #ca14de, stroke-width: 2.5px;
    classDef output stroke: #ede205 , stroke-width: 2.5px;
    classDef empty width: 0px, height: 0px;
```

