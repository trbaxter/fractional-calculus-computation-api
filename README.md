# Fractional Calculus Computation API

This is a Java-based API that can produce expressions for derivatives and integrals of
user-submitted expressions of integer or fractional order.

---

## Table of Contents

[Project Status](#project-status)  
[Technologies Used](#technologies-used)  
[Getting Started](#getting-started)
[Endpoint Information](#endpoint-information)  
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

---

## Project Status

This project is in active development with a current version of 1.0.0.  
Upcoming features include:

- Support for other types of expressions (logarithms, trig identities, etc.)

- Support for mixed expressions

- Support for nested functions

- Frontend UI

---

## Technologies Used

### Core Technologies

- **Java**: OpenJDK Version 22.0.1

### Build Tools

- **Maven**: Version 3.9.6

### Frameworks

- **Spring Boot**: Version 3.2.5
- **Spring MVC**: Version 6.1.6

### Testing

- **JUnit**: JUnit 5 (JUnit Jupiter) Version 5.10.2
- **Mockito**: Version 5.11.0

### Libraries

- **Apache Commons Math**: Version 3.6.1
- **Jackson Databind**: Version 2.15.4
- **Logback**: Version 1.4.14
- **SLF4J**: Version 2.0.13
- **Hibernate Validator**: Version 8.0.1.Final

---

## Getting Started

Clone the repository:

```sh
git clone https://github.com/trbaxter/fractional-computation-api.git
```

<br/>


Navigate to the project directory:

```sh
cd fractional-computation-api
```

<br/>


Build the project using Maven:

```sh
mvn clean install
```

<br/>

Start the application using the following command:

```sh
mvn spring-boot:run
```

<br/>

Upon successful start, endpoints may be accessed by using cURL commands or API testing software
(Postman / Insomnia / etc.).

---

## Endpoint Information

### 1.) Caputo Fractional Derivative Endpoint

Method: <b>POST</b>

Endpoint URL: `/fractional-calculus-computation-api/derivative/caputo`



Required request body:

```
{
  "coefficients": [],
  "order": 
}
```

Parameters:

```coefficients``` - An array of polynomial coefficients of type double or integer.

```order``` - The derivative order of type double.

Response:

```
{
    "expression": 
}
```

Returns the closed-form expression of the Caputo derivative if successful.

<br/>
<br/>

### 2.) Riemann-Liouville Fractional Derivative Endpoint

Method: <b>POST</b>

Endpoint URL: `/fractional-calculus-computation-api/derivative/riemann-liouville`

Required request body:

```
{
  "coefficients": [],
  "order": 
}
```

Parameters:

```coefficients``` - An array of polynomial coefficients of type double or integer.

```order``` - The derivative order of type double.



Response:

```
{
    "expression": 
}
```

Returns the closed-form expression of the Riemann-Liouville derivative if successful.

<br/>

### 3.) Caputo Fractional Integral Endpoint

Method: <b>POST</b>

Endpoint URL: `/fractional-calculus-computation-api/integral/caputo`

Required request body:

```
{
  "coefficients": [],
  "order": 
}
```

Parameters:

```coefficients``` - An array of polynomial coefficients of type double or integer.

```order``` - The derivative order of type double.

Response:

```
{
    "expression": 
}
```

Returns the closed-form expression of the Caputo integral if successful.

---

## Examples

### Example #1: Caputo Derivative with Non-Zero Integer Coefficients

**Goal**: Obtain an expression for the 0.35th Caputo derivative of $3x^2 + 2x + 1$.

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
  "expression": "4.040x^1.65 + 2.222x^0.65"
}
```

<br/>
<br/>

### Example #2: Caputo Derivative with Zero and Non-Zero Coefficients

**Goal**: Obtain an expression for the 1.23456th Caputo derivative of $14.6x^3 + 16.049x - 12$.

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
    "expression": "53.778x^1.76544"
}
```

<br/>
<br/>

### Example #3: Riemann-Liouville Derivative with Non-Zero Integer Coefficients

**Goal**: Obtain an expression for the 0.35th Riemann-Liouville derivative of $3x^2 + 2x + 1$.

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
  "expression": "4.040x^1.65 + 2.222x^0.65 + 0.722x^-0.35"
}
```

&nbsp;

<br/>
<br/>

### Example #4: Riemann-Liouville Derivative with Zero and Non-Zero Coefficients

**Goal**: Obtain an expression for the 1.23456th Riemann-Liouville derivative of $14.6x^3 + 16.
049x - 12$.

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
  "expression": "53.778x^1.76544 + 13.314x^-0.23456 + 2.335x^-1.23456"
}
```

<br/>
<br/>

### Example #5: Caputo Integral with Non-Zero Integer Coefficients

**Goal**: Obtain an expression for the 0.35th Caputo integral of $3x^2 + 2x + 1$.

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
  "expression": "2.122x^2.35 + 1.662x^1.35 + 1.122x^0.35 + C"
}
```

---

## Calculation Details

<details>
<summary>Caputo Fractional Derivative of $3x^2 + 2x + 1$ with $\alpha = 0.35$</summary>
<p>The Caputo fractional derivative of order $\alpha$ for a function $f(x)$ is defined as:</p>
$${}^{C} D^{0.5} f(x) = \dfrac{1}{\Gamma(n-\alpha)} \int_{0}^{x} \dfrac{f^{(n)}(t)}{(x-t^
{\alpha + 1 - n})}dt $$
<p>Where:  
- $n = ⌈\alpha⌉$ (the smallest integer greater than or equal to $\alpha$
- $\Gamma$ is the Gamma function
</p>

</details>

Given the polynomial $f(x) = 3x^2 + 2x + 1$, and the order $\alpha = 0.5$, the Caputo fractional
derivative
${}^{C} D^{0.5} f(x)$ is computed by applying the Caputo derivative formula to each term:

Term $3x^2$:

$$
{}^{C} D^{0.5}{\text{&nbsp;}}[3x^2] = 3 \cdot \dfrac{\Gamma(3)}{\Gamma(3-0.5)} x^{2-0.5} = 3 \cdot
\dfrac{2!}{\Gamma(
2.5)} x^{1.5}
$$

&nbsp;

Since $\Gamma(3) = 2$ amd $\Gamma(2.5) = \dfrac{3\sqrt{\pi}}{4}$:

&nbsp;

$$
{}^{C} D^{0.5}{\text{&nbsp;}}[3x^2] = 3 \cdot \dfrac{2}{\dfrac{3\sqrt{\pi}}{4}}x^{1.5} =
\dfrac{8}{\sqrt{\pi}}x^{1.5}
$$

&nbsp;

Term $2x$:

$$
{}^{C} D^{0.5}{\text{&nbsp;}}[2x] = 2 \cdot \dfrac{\Gamma(2)}{\Gamma(2 - 0.5)} x^{1-0.5} = 2 \cdot
\dfrac{1!}{\Gamma(
1.5)} x^{0.5}
$$

&nbsp;

Since $\Gamma(2) = 1$ and $\Gamma(1.5) = \dfrac{\sqrt{\pi}}{2}$:

&nbsp;

$$
{}^{C} D^{0.5}{\text{&nbsp;}}[2x] = 2 \cdot \dfrac{1}{\dfrac{\sqrt{\pi}}{2}}x^{0.5} =
\dfrac{4}{\sqrt{\pi}}x^{0.5}
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
    A fractional derivative is a generalization of the traditional integer-order derivative extended 
    to include non-integer values.
</details>

<br/>

<details>
    <summary>&nbsp;<i>Why is this important?</i>&nbsp;<br/></summary>&nbsp;<br/>
    This type of analytical technique is particularly useful for investigating or modeling physical phenomena 
    that exhibit memory effects or hereditary properties in its behavior.
</details>


<br/>

<details>
    <summary>&nbsp;<i>What is meant by "memory effects"?</i></summary>&nbsp;<br/>
    "Memory effects" refers to how a system's <i><b>recent</b></i> past influences its present behavior. <br/> 
    In other words, the system "remembers" its recent history. <br/><br/>
    For example, consider a rubber band that's been stretched and released multiple times. 
    The current "stretchiness" of the rubber band not only depends on how it's being stretched right now, but 
    <i><b>also</b></i> on how it was stretched recently.
</details>

<br/>

<details>
    <summary>&nbsp;<i>What about "hereditary properties"?</i></summary>&nbsp;<br/>
    "Hereditary properties" refers to the characteristics of a system that depend on its <i><b>entire</b></i> history. 
    <br/><br/>
    As an example, consider a material that hardens over time, like concrete. The current "hardness" of concrete is a 
    comprehensive function of its entire history - the starting mix ratio of cement and water, the curing conditions, 
    the amount of cumulative elemental exposure - all of these historical factors represent the hereditary properties 
    of the material.
</details>

<br/>

<details>
    <summary>&nbsp;<i>How does all this relate to the Caputo and Riemann-Liouville derivatives?</i></summary>&nbsp;<br/>
    These two derivatives give us an option to select how much of a system's "memory" we wish to consider in 
    the mathematical analysis of a given phenomena. <br/><br/>
    If only a portion of a system's "memory" is needed, then the Caputo derivative is used. <br/>
    If the entire system's "memory" is needed, then the Riemann-Liouville derivative is used. 
</details>

<br/>

<details>
    <summary>&nbsp;<i>There are multiple coefficients in my input, but the output doesn't show the same amount. Why?</i>
    </summary>&nbsp;<br/>
    There are two reasons why this occurs:&nbsp;<br/><br/>
    <details>
        <summary>&nbsp; Fractional Derivatives of Constants</summary>&nbsp;<br/>
        For an array with multiple coefficients, the right-most coefficient represents a constant term, and the 
        fractional derivative of a constant is always zero for a Caputo fractional derivative. <br/><br/>
        If the fractional derivative of a constant is needed, use the Riemann-Liouville option.
    </details> <br/>
    <details>
        <summary>&nbsp; General Behavior of a Caputo Derivative</summary>&nbsp;<br/>
        For a Caputo derivative, if the exponent value of the term minus the order value is a negative number, then that 
        term's calculation will be omitted from the result. This is due to the way in which the Caputo derivative is 
        designed to handle "well-behaved" finite functions. 
        <br/><br/>
        Consider the following example where $f(x) = x$ and $\alpha = 2$: <br/><br/>
        ${}^{C} D^{2}{\text{&nbsp;}}[x] = \dfrac{\Gamma(2)}{\Gamma(2-2)}x^{1-2} = \dfrac{1}{\Gamma(0)}x^{-1}$
        <br/><br/>
        Since $\Gamma(0)$ is undefined, this result would be omitted from the output expression. 
        <br/>
        This applies to negative values of the gamma function as well.
    </details>
</details>

<br/>

<details>
    <summary>&nbsp;<i>Why are there two derivative endpoints, but only one integration endpoint?</i></summary>&nbsp;<br/>
    This is because the Caputo and Riemann-Liouville techniques treat derivatives of constant values differently.
    <br/>
    <br/>
    This distinction is not present in the API integration process, and both methods lead to the same results.<br/>
</details>


<br/>

## Contributing

Contributions towards this project are welcome! If interested, please follow these steps:

1.) Fork the repository

2.) Create a new branch (`git checkout -b feature-branch`)

3.) Commit your changes (`git commit -m 'Add some feature'`)

4.) Push to the branch (`git push origin feature-branch`)

5.) Open a pull request

Remember to update tests if necessary and provide responses to the questions as outlined in the
pull request template.

<br/>

## Changelog

### Version [1.0.0] - Released 2024-06-TBD

- Initial release with Caputo and Riemann-Liouville fractional derivative and Caputo fractional
  integration capabilities


- Includes comprehensive test coverage

<br/>

## Known Issues

- When polynomial coefficients are very large, or very small, numerical precision errors may
  occur.


- The current implementation does not support multi-threading for large-scale computations.

<br/>

## License

This project is licensed under the MIT License. See the LICENSE file for details.

<br/>

## Acknowledgements

- <a href="https://spring.io/projects/spring-boot">Spring Boot</a>


- <a href="https://maven.apache.org/">Maven</a>


- <a href="https://commons.apache.org/proper/commons-math/">Apache Commons Math</a>


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
