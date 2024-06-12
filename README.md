# Fractional Calculus Computation API

A Java-based API for computing Caputo and Riemann-Liouville fractional derivatives of user-provided polynomial
expressions. 

## Table of Contents
[Project Status](#project-status)  
[Technologies Used](#technologies-used)  
[Getting Started](#getting-started)  
[Usage](#usage)  
[API Reference](#api-reference)  
[Examples](#examples)  
[Calculation Details](#calculation-details)  
[FAQ](#faq)  
[Contributing](#contributing)  
[Changelog](#changelog)  
[Known Issues](#known-issues)  
[License](#license)  
[Acknowledgements](#acknowledgements)  
[Support](#support)  
[Contact Information](#contact-information)

<br/>

## Project Status

This project is in active development with a current version of 1.0.0. Upcoming features include:


- Support for the Caputo and Riemann-Liouville fractional integrals


- Performance optimizations


- Expanding capability to handle other types of math expressions  
(logarithms, trig identities, and so on)

<br/>
    
## Technologies Used

- Java 11 (or higher)


- Maven

<br/>

  
## Getting Started


### Prerequisites

1.) Clone the repository:  
```sh
git clone https://github.com/trbaxter/fractional-computation-api.git
```
&nbsp;

2.) Navigate to the project directory: 
```sh
cd fractional-computation-api
```
&nbsp;

3.) Build the project using Maven:
```sh
mvn clean install
```

<br/>


## Usage

### Application Start

To start the application, use the following command:
```sh
mvn spring-boot:run
```
<br/>


## API Reference


### Caputo Fractional Derivative Endpoint  


Endpoint URL: 
```
/fractional-calculus-computation-api/derivative/caputo
```

Method: <b>POST</b>  

&nbsp;

Required request body:
```
{
  "coefficients": [],
  "order": 
}
```
&nbsp;

Parameters:

```coefficients``` - An array of polynomial coefficients of type double.

```order``` - The derivative order of type double.  
&nbsp;

Response:
```
{
    "expression": 
}
```
Returns the closed-form expression of the Caputo derivative if successful.

&nbsp;

### Riemann-Liouville Fractional Derivative Endpoint


Endpoint URL:
```
/fractional-calculus-computation-api/derivative/riemann-liouville
```

Method: <b>POST</b>

&nbsp;

Required request body:
```
{
  "coefficients": [],
  "order": 
}
```
&nbsp;

Parameters:

```coefficients``` - An array of polynomial coefficients of type double.

```order``` - The derivative order of type double.  
&nbsp;

Response:
```
{
    "expression": 
}
```
Returns the closed-form expression of the Riemann-Liouville derivative if successful.

&nbsp;

## Examples

### Example 1: Caputo Derivative with Non-Zero Integer Coefficients

Calculate the closed-form 0.35th Caputo derivative of $3x^2 + 2x + 1$.

&nbsp;

Input: 

```
{
  "coefficients": [3,2,1],
  "order": 0.35
}
```

&nbsp;

Output: 
```
{
  "expression": "4.040x^1.650 + 2.222x^0.650"
}
```
<br/>
<br/>

### Example 2: Caputo Derivative with Zero and Non-Zero Coefficients

Calculate the closed-form 1.23456th Caputo derivative of $14.6x^3 + 16.049x - 12$.

&nbsp;

Input:
```
{
  "coefficients": [14.6, 0, 16.049, -12],
  "order": 1.23456
}
```

&nbsp;

Output:
```
{
    "expression": "53.778x^1.766"
}
```

<br/>
<br/>

### Example 3: Riemann-Liouville Derivative with Non-Zero Integer Coefficients

Calculate the closed-form 0.35th Riemann-Liouville derivative of $3x^2 + 2x + 1$.

&nbsp;

Input:

```
{
  "coefficients": [3,2,1],
  "order": 0.35
}
```

&nbsp;

Output:
```
{
  "expression": "4.040x^1.650 + 2.222x^0.650 + 0.722x^-0.350"
}
```

> [!NOTE]
> Be aware that the Riemann-Liouville fractional derivative has non-zero outputs for derivatives of constants. 

<br/>
<br/>

### Example 4: Riemann-Liouville Derivative with Zero and Non-Zero Coefficients

Calculate the closed-form 0.35th Riemann-Liouville derivative of $14.6x^3 + 16.049x - 12$.

&nbsp;

Input:

```
{
  "coefficients": [3,2,1],
  "order": 0.35
}
```

&nbsp;

Output:
```
{
  "expression": "4.514x^1.500 + 2.257x^0.500 + 0.564x^-0.500"
}
```

## Calculation Details

Given the polynomial $f(x) = 3x^2 + 2x + 1$, and the order $\alpha = 0.5$, the Caputo fractional derivative
${}^{C} D^{0.5} f(x)$ is computed by applying the Caputo derivative formula to each term: 

Term $3x^2$:

$$
{}^{C} D^{0.5}{\text{&nbsp;}}[3x^2] = 3 \cdot \dfrac{\Gamma(3)}{\Gamma(3-0.5)} x^{2-0.5} = 3 \cdot \dfrac{2!}{\Gamma(2.5)} x^{1.5}
$$

&nbsp;

Since $\Gamma(3) = 2$ amd $\Gamma(2.5) = \dfrac{3\sqrt{\pi}}{4}$:

&nbsp;

$$
{}^{C} D^{0.5}{\text{&nbsp;}}[3x^2] = 3 \cdot \dfrac{2}{\dfrac{3\sqrt{\pi}}{4}}x^{1.5} = \dfrac{8}{\sqrt{\pi}}x^{1.5}
$$

&nbsp;

Term $2x$:

$$
{}^{C} D^{0.5}{\text{&nbsp;}}[2x] = 2 \cdot \dfrac{\Gamma(2)}{\Gamma(2 - 0.5)} x^{1-0.5} = 2 \cdot \dfrac{1!}{\Gamma(1.5)} x^{0.5}
$$

&nbsp;

Since $\Gamma(2) = 1$ and $\Gamma(1.5) = \dfrac{\sqrt{\pi}}{2}$:

&nbsp;

$$
{}^{C} D^{0.5}{\text{&nbsp;}}[2x] = 2 \cdot \dfrac{1}{\dfrac{\sqrt{\pi}}{2}}x^{0.5} = \dfrac{4}{\sqrt{\pi}}x^{0.5}
$$

&nbsp;

Term $1$:

$$
{}^{C} D^{0.5}{\text{&nbsp;}}[1] = 0
$$

&nbsp;

Simplifying and combining the terms:

&nbsp;

$$
{}^{C} D^{0.5}{\text{&nbsp;}}f(x) = \dfrac{8}{\sqrt{\pi}}x^{1.5} + \dfrac{4}{\sqrt{\pi}}x^{0.5}
$$

&nbsp;

Approximating to 3 decimal places in the coefficient and 1 decimal place in the exponent:

$$
\approx 4.514x^{1.5} + 2.257x^{0.5}
$$

<br/>

## FAQ

<details>
    <summary>&nbsp;<i>What is a fractional derivative?</i></summary>&nbsp;<br/>
    A fractional derivative is a generalization of the traditional integer-order derivative extended to include non-integer values.
</details>


&nbsp;

<details>
    <summary>&nbsp;<i>Why is this important?</i>&nbsp;<br/></summary>&nbsp;<br/>
    This type of analytical technique is particularly useful for investigating or modeling physical phenomena 
    that exhibit memory effects or hereditary properties in its behavior.
</details>


&nbsp;

<details>
    <summary>&nbsp;<i>What is meant by "memory effects"?</i></summary>&nbsp;<br/>
    "Memory effects" refers to how a system's <i><b>recent</b></i> past influences its present behavior. <br/> 
    In other words, the system "remembers" its recent history. <br/><br/>
    For example, consider a rubber band that's been stretched and released multiple times. The current "stretchiness" of 
    the rubber band not only depends on how it's being stretched right now, but <i><b>also</b></i> on how it was stretched recently.
</details>


&nbsp;

<details>
    <summary>&nbsp;<i>What about "hereditary properties"?</i></summary>&nbsp;<br/>
    "Hereditary properties" refers to the characteristics of a system that depend on its <i><b>entire</b></i> history. <br/><br/>
    As an example, consider a material that hardens over time, like concrete. The current "hardness" of concrete is a 
    comprehensive function of its entire history - the starting mix ratio of cement and water, the curing conditions, 
    the amount of cumulative elemental exposure - all of these historical factors represent the hereditary properties 
    of the material.
</details>


&nbsp;


<details>
    <summary>&nbsp;<i>How does all this relate to the Caputo and Riemann-Liouville derivatives?</i></summary>&nbsp;<br/>
    These two derivatives give us an option to select how much of a system's "memory" we wish to consider in 
    the mathematical analysis of a given phenomena. <br/><br/>
    If only a portion of a system's "memory" is needed, then the Caputo derivative is used. <br/>
    If the entire system's "memory" is needed, then the Riemann-Liouville derivative is used. 
</details>


&nbsp;

<details>
    <summary>&nbsp;<i>There are multiple coefficients in my input, but the output doesn't show the same amount. Why?</i></summary>&nbsp;<br/>
    There are two reasons why this occurs:&nbsp;<br/><br/>
    <details>
        <summary>&nbsp; Fractional Derivatives of Constants</summary>&nbsp;<br/>
        For an array with multiple coefficients, the right-most coefficient represents a constant term, and the fractional 
        derivative of a constant is always zero in either Caputo or Riemann-Liouville contexts.
    </details>
    <details>
        <summary>&nbsp; General Behavior of a Caputo Derivative</summary>&nbsp;<br/>
        For a Caputo derivative, if the exponent value of the term minus the order value is a negative number, then that 
        term's calculation will be omitted from the result. This is due to the way in which the Caputo derivative is designed 
        to handle "well-behaved" finite functions. <br/><br/>
        Consider the following example where $f(x) = x$ and $\alpha = 2$: <br/><br/>
        ${}^{C} D^{2}{\text{&nbsp;}}[x] = \dfrac{\Gamma(2)}{\Gamma(2-2)}x^{1-2} = \dfrac{1}{\Gamma(0)}x^{-1}$
        <br/><br/>
        Since $\Gamma(0)$ is undefined (it tends to infinity), this result would be omitted from the output expression. <br/>
        This applies to negative values of the gamma function as well.
    </details>
</details>





<br/>

## Contributing

Contributions towards this project are welcome! If interested, please follow these steps:

1.) Fork the repository

2.) Create a new branch (`git checkout -b feature-branch`)

3.) Commit your changes (`git commit -m 'Add some feature'`)

4.) Push to the branch (`git push origin feature-branch`)

5.) Open a pull request

Remember to update tests if necessary and provide responses to the questions as outlined in the pull request template. 

<br/>

## Changelog

### Version [1.0.0] - Released 2024-06-15

- Initial release with Caputo and Riemann-Liouville fractional derivative calculation capabilities

- Includes comprehensive test coverage

<br/>

## Known Issues

- When polynomial coefficients are very large, or very small, numerical precision errors may occur. 


- The current implementation does not support multi-threading for large-scale computations.

<br/>

## License

This project is licensed under the MIT License - see the LICENSE file for details. 

<br/>

## Acknowledgements

- <a href="https://spring.io/projects/spring-boot">Spring Boot</a> 


- <a href="https://maven.apache.org/">Maven</a>


- <a href="https://openai.com/">OpenAI GPT-4o</a>

<br/>

## Support

For any questions or assistance, please feel free to reach out to the project creator via email.  
(trb7074@gmail.com)

<br/>

## Contact Information

**Author:** Tyler Baxter

**Email:** trb7074@gmail.com

**GitHub:** trbaxter
