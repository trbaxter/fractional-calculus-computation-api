<!-- @formatter:off -->
# Fractional Calculus Computation API

This is a Java-based API that can produce expressions for derivatives and integrals of
user-submitted expressions of integer or fractional order.

<br />

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

<br />

## Project Status

This project is in active development with a current version of 1.0.0.  
Upcoming features include:

- Support for other types of expressions (logarithms, trig identities, etc.)

- Support for mixed expressions

- Support for nested functions

- Frontend UI

<br />

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

<br />

## Getting Started

Clone the repository:

```sh
git clone https://github.com/trbaxter/fractional-computation-api.git
```

<br />

Navigate to the project directory:

```sh
cd fractional-computation-api
```

<br />

Build the project using Maven:

```sh
mvn clean install
```

<br />

Start the application using the following command:

```sh
mvn spring-boot:run
```

<br />

Upon successful start, endpoints may be accessed by using cURL commands or API testing software.

<br />

## Endpoint Information

All endpoints use the following request body format:

```json
{
  "coefficients": [],
  "order": ""
}
```

`coefficients` - An array of polynomial coefficients of type double or integer.  
`order` - Operation order value. Can be integer, zero, or non-integer of type double.

<br />

The API response output is also the same for all endpoints: 

```json
{
  "expression": ""
}
```

<br />

<strong>Caputo Fractional Derivative</strong>  
HTTP Verb: POST  
URL: `/fractional-calculus-computation-api/derivative/caputo`  
Returns the closed-form expression of the Caputo fractional derivative.

<br />

<strong>Riemann-Liouville Fractional Derivative</strong>  
HTTP Verb: POST  
Endpoint URL: `/fractional-calculus-computation-api/derivative/riemann-liouville`  
Returns the closed-form expression of the Riemann-Liouville fractional derivative.

<br />

<strong>Caputo Fractional Integral Endpoint</strong>  
HTTP Verb: POST  
Endpoint URL: `/fractional-calculus-computation-api/integral/caputo`  
Returns the closed-form expression of the Caputo fractional integral.

<br />

## Examples

<details>
<summary>
0.35724th Caputo Fractional Derivative of 4.27x² + 2.016x + 1
</summary>
  
<br />

<table>
<tr>
<td>

Input:

```json
{
  "coefficients": [4.27, 2.016, 1],
  "order": "0.35724"
}
```

<br />

API Output:

```json
{
  "expression": "5.782x^1.64276 + 2.242x^0.64276"
}
```

Notice that the derivative of the constant term is 0 using the Caputo method.

</td>
</tr>
</table>
</details>

<br />

<details>
<summary>
3.14159th Riemann-Liouville Fractional Derivative of 3x² + 2x + 1
</summary>
  
<br />

<table>
<tr>
<td>

Input:

```json
{
  "coefficients": [3, 2, 1],
  "order": "3.14159"
}
```

<br />

API Output:

```json
{
  "expression": "-0.769x^-1.14159 + 0.293x^-2.14159 - 0.313x^-3.14159"
}
```

The derivative of a constant term using the Riemann-Liouville method does <i><strong>not</strong></i> result in zero.  
It treats constants in the expression as coefficients of 0th-power variables (e.g. 1x⁰ instead of just 1).

</td>
</tr>
</table>
</details>

<br />

<details>
<summary>
1.79th Caputo Fractional Integral of 3x³ - x + 12
</summary>
  
<br />

<table>
<tr>
<td>

Input:

```json
{
  "coefficients": [3, 0, -1, 12],
  "order": "1.79"
}
```

<br />

Output:

```json
{
  "expression": "0.214x^4.79 - 0.216x^2.79 + 7.218x^1.79 + C"
}
```

The Caputo and Riemann-Liouville fractional integrals return the same value for the current API functionality.  
Thus, only the Caputo endpoint is provided to reduce code duplication.

</td>
</tr>
</table>
</details>

<br />

## Calculation Details

For further details on the mathematics behind the fractional derivative and fractional 
integral operations, please see the documentation folder.

<details>
<summary>
Riemann-Liouville Fractional Derivative
</summary>

<br />

<table>
<tr>
<td>

<section>

$\Large \underline{\text{Derivative Definition}}$

The Riemann-Liouville fractional derivative of order $\alpha$ for a generic function $f(x)$ is defined as:
$${}^{RL} D^{\alpha} f(x) = \dfrac{1}{\Gamma(n-\alpha)} \dfrac{\mathrm{d} ^{n}}{\mathrm{d} x^{n}} \Biggl( \int_{0}^{x} (x-t)^{n-\alpha-1}f(t) \ \mathrm{d} t \Biggl)$$
Where:
- ${}^{RL}$ indicates that the fractional derivative being performed is the Riemann-Liouville type
- $n = \lceil \alpha \rceil$ is the ceiling of $\alpha$ (the smallest integer $\geq$ to $\alpha$)
- $\Gamma$ is the Gamma function
- $f(t)$ is the same expression as $f(x)$, except with $t$ instead of $x$ 
</section>

<br />

<br />

<br />

<section>

$\Large \underline{\text{Using the Derivative on an Example Polynomial Function}}$

Let $f(x) = 3x^2 + 2x + 1$ and $\alpha = 0.35$. For this order value, $\lceil0.35\rceil = 1$.  
Plugging these into the definition:
$${}^{RL} D^{0.35} (3x^2 + 2x + 1) = \dfrac{1}{\Gamma(0.65)} \dfrac{\mathrm{d} }{\mathrm{d} x} \Biggl( \int_{0}^{x} (x-t)^{-0.35}(3t^2 + 2t + 1) \ \mathrm{d} t \Biggl)$$
Expanding the integral within the parentheses with factored out constants:
$$= \dfrac{1}{\Gamma(0.65)} \dfrac{\mathrm{d} }{\mathrm{d} x} \Biggl(3\int_{0}^{x} \Big( (x-t)^{-0.35} \cdot t^2 \Big) \ \mathrm{d} t \ \ + \ 2\int_{0}^{x} \Big( (x-t)^{-0.35} \cdot t \Big) \ \mathrm{d} t \ \ + \ \int_{0}^{x} \Big( (x-t)^{-0.35} \Big) \ \mathrm{d} t \Biggl)$$
A $u$-substitution will be necessary for each of the three integrals.  

Let $u = x - t$ and $du = -dt$.  
The lower limit of $t$ is zero. Therefore, the lower limit of $u$ is $x - 0 = x$.  
Similarly, the upper limit of $t$ is $x$. Therefore, the upper limit of $u$ is $x - x = 0$. 
</section>

<br />

<br />

<br />

<section>
  
$\Large \underline{\text{U-Substitution of the First Integral}}$

The $u$-form of the first integral is:
$$3 \int_{x}^{0} \Big( u^{-0.35}(x - u)^2 \Big)\big(- \mathrm{d} u \big)$$
Factoring the negative sign outside the integral:
$$-3\int_{x}^{0} \Big( u^{-0.35}(x - u)^2 \Big) \ \mathrm{d} u$$

Expanding the squared term in the integrand:
$$-3\int_{x}^{0} u^{-0.35} (x^2 \ - \ 2xu \ + \ u^2) \ \mathrm{d} u$$
Distributing the contents of the integrand:
$$-3\int_{x}^{0} \big(x^2u^{-0.35} \ - \ 2xu^{0.65} \ + \ u^{1.65}\big) \ \mathrm{d} u$$
Applying linearity of integration:
$$-3\Bigg[\ \int_{x}^{0} x^2u^{-0.35} \ \mathrm{d} u\ - \int_{x}^{0} 2xu^{0.65} \ \mathrm{d} u\ + \int_{x}^{0} u^{1.65} \ \mathrm{d} u \ \Bigg]$$
Factoring out constants:  
$$-3\Bigg[ \ x^2 \cdot \int_{x}^{0} u^{-0.35} \mathrm{d} u \ - \ 2x \cdot \int_{x}^{0} u^{0.65} \mathrm{d} u \ + \int_{x}^{0} u^{1.65} \mathrm{d} u \ \Bigg]$$
Performing the integration:
$$-3\Bigg[ \ x^2 \cdot \left\lbrack \dfrac{u^{0.65}}{0.65} \right\rbrack_x^0 \ - \ 2x \cdot \left\lbrack \dfrac{u^{1.65}}{1.65} \right\rbrack_x^0 \ + \left\lbrack \dfrac{u^{2.65}}{2.65} \right\rbrack_x^0 \ \ \Bigg]$$
Evaluating:
$$-3\Bigg[ \ x^2 \cdot \bigg(0 - \dfrac{x^{0.65}}{0.65} \bigg) \ - \ 2x \cdot \bigg(0 - \dfrac{x^{1.65}}{1.65} \bigg) \ + \ \bigg(0 - \dfrac{x^{2.65}}{2.65} \bigg) \Bigg]$$
Simplifying the result:
$$-3\Bigg[ \ x^2 \cdot \bigg(-\dfrac{x^{0.65}}{0.65} \bigg) \ - \ 2x \cdot \bigg(-\dfrac{x^{1.65}}{1.65} \bigg) \ + \ \bigg(-\dfrac{x^{2.65}}{2.65} \bigg) \Bigg]$$
$$-3\Bigg[ \ \bigg(-\dfrac{x^{2.65}}{0.65} \bigg) \ + \ \bigg(\dfrac{2x^{2.65}}{1.65} \bigg) \ - \ \bigg(\dfrac{x^{2.65}}{2.65} \bigg) \Bigg]$$
$$3\Bigg[ \ \bigg(\dfrac{x^{2.65}}{0.65} \bigg) \ - \ \bigg(\dfrac{2x^{2.65}}{1.65} \bigg) \ + \ \bigg(\dfrac{x^{2.65}}{2.65} \bigg) \Bigg]$$
$$\Bigg[ \ \bigg(\dfrac{3}{0.65} \bigg) \ - \ \bigg(\dfrac{6}{1.65} \bigg) \ + \ \bigg(\dfrac{3}{2.65} \bigg) \Bigg] x^{2.65}$$
$$\bigg( \dfrac{16,000}{7,579} \bigg) x^{2.65}$$
</section>

<br />

<br />

<br />

<section>

$\Large \underline{\text{U-Substitution of the Second Integral}}$

The $u$-form of the second integral is:
$$2 \int_{x}^{0} \Biggl( u^{-0.35}(x-u)\Biggl)(- \mathrm{d} u)$$
Factoring the negative sign outside the integral:
$$-2 \int_{x}^{0} \Biggl( u^{-0.35}(x-u) \Biggl) \mathrm{d} u$$
Distributing the integrand:
$$-2\int_{x}^{0} \Biggl( xu^{-0.35} - u^{0.65} \Biggl) \mathrm{d} u$$
Applying linearity of integration:
$$-2 \Bigg[ \int_{x}^{0} xu^{-0.35} \mathrm{d} u \ - \ \int_{x}^{0} u^{0.65} \mathrm{d} u \Bigg]$$
Factoring out $x$ from the first integrand:
$$-2 \Bigg[ x \cdot \int_{x}^{0} u^{-0.35} \mathrm{d} u \ - \ \int_{x}^{0} u^{0.65} \mathrm{d} u \Bigg]$$
Performing the integration:
$$-2 \Bigg[ \ x \cdot \left\lbrack \dfrac{u^{0.65}}{0.65} \right\rbrack_x^0 \ - \ \left\lbrack \dfrac{u^{1.65}}{1.65} \right\rbrack_x^0 \ \Bigg]$$
Evaluating:
$$-2 \Bigg[ \ x \cdot \bigg( 0 \ - \ \dfrac{x^{0.65}}{0.65} \bigg) \ - \ \bigg( 0 \ - \ \dfrac{x^{1.65}}{1.65} \bigg) \ \Bigg]$$
Simplifying the result:
$$-2 \Bigg[ \ x \cdot \bigg( - \dfrac{x^{0.65}}{0.65} \bigg) \ - \ \bigg( - \dfrac{x^{1.65}}{1.65} \bigg) \ \Bigg]$$
$$-2 \Bigg[ \ \bigg( - \dfrac{x^{1.65}}{0.65} \bigg) \ + \ \bigg( \dfrac{x^{1.65}}{1.65} \bigg) \ \Bigg]$$
$$\Bigg[ \ \bigg(\dfrac{2x^{1.65}}{0.65} \bigg) \ - \ \bigg( \dfrac{2x^{1.65}}{1.65} \bigg) \ \Bigg]$$
$$\Bigg[ \ \bigg(\dfrac{2}{0.65} \bigg) \ - \ \bigg( \dfrac{2}{1.65} \bigg) \ \Bigg] x^{1.65}$$
$$\bigg( \dfrac{800}{429} \bigg) x^{1.65}$$
</section>

<br />

<br />

<section>

$\Large \underline{\text{U-Substitution of the Third Integral}}$

The $u$-form of the third integral is:
$$\int_x^0 (u^{-0.35})(- \mathrm{d} u)$$
Factoring the negative sign outside the integral:
$$- \int_x^0 (u^{-0.35}) \mathrm{d} u$$
Performing the integration:
$$- \large\left\lbrack \dfrac{u^{0.65}}{0.65} \large\right\rbrack_x^0$$
Evaluating:
$$-\bigg(0 - \dfrac{x^{0.65}}{0.65} \bigg)$$
Simplifying:
$$\bigg(\dfrac{20}{13}\bigg)x^{0.65}$$
</section>

<br />

<br />

<section>

$\Large \underline{\text{Using the Results of the Three Integrals}}$

Substituting the results of the three integrals into the previous derivative expression:
$${}^{RL} D^{0.35} (3x^2 + 2x + 1) = \dfrac{1}{\Gamma(0.65)} \dfrac{d}{dx} \bigg[ \bigg( \dfrac{16,000}{7,579} \bigg) x^{2.65} \ + \ \bigg( \dfrac{800}{429} \bigg) x^{1.65} \ + \ \bigg(\dfrac{20}{13}\bigg)x^{0.65} \bigg]$$
Performing the derivative:
$$= \dfrac{1}{\Gamma(0.65)} \bigg[ \bigg( \dfrac{16,000 \cdot 2.65}{7,579} \bigg) x^{1.65} \ + \ \bigg( \dfrac{800 \cdot 1.65}{429} \bigg) x^{0.65} \ + \ \bigg( \dfrac{20 \cdot 0.65}{13}\bigg)x^{-0.35} \bigg]$$
Using $\Gamma(0.65) \approx 1.385$:
$$= \bigg[ \bigg( \dfrac{16,000 \cdot 2.65}{7,579 \cdot 1.385} \bigg) x^{1.65} \ + \ \bigg( \dfrac{800 \cdot 1.65}{429 \cdot 1.385} \bigg) x^{0.65} \ + \ \bigg( \dfrac{20 \cdot 0.65}{13 \cdot 1.385}\bigg)x^{-0.35} \bigg]$$
Simplifying to three decimal places:
$$\boxed{\approx &thinsp; 4.040 x^{1.65} + 2.222 x^{0.65} + 0.722 x^{-0.35}}$$
</section>

</td>
</tr>
</table>
</details>

<br />

## FAQ

<details>
    <summary>&nbsp;<i>What is a fractional derivative?</i></summary>&nbsp;<br/>
    A fractional derivative is a generalization of the traditional integer-order derivative extended 
    to include non-integer values.
</details>

<br />

<details>
    <summary>&nbsp;<i>Why is this important?</i>&nbsp;<br/></summary>&nbsp;<br/>
    This type of analytical technique is particularly useful for investigating or modeling physical phenomena 
    that exhibit memory effects or hereditary properties in its behavior.
</details>

<br />

<details>
    <summary>&nbsp;<i>What is meant by "memory effects"?</i></summary>&nbsp;<br/>
    "Memory effects" refers to how a system's <i><b>recent</b></i> past influences its present behavior. <br/> 
    In other words, the system "remembers" its recent history. <br/><br/>
    For example, consider a rubber band that's been stretched and released multiple times. 
    The current "stretchiness" of the rubber band not only depends on how it's being stretched right now, but 
    <i><b>also</b></i> on how it was stretched recently.
</details>

<br />

<details>
    <summary>&nbsp;<i>What about "hereditary properties"?</i></summary>&nbsp;<br/>
    "Hereditary properties" refers to the characteristics of a system that depend on its <i><b>entire</b></i> history. 
    <br/><br/>
    As an example, consider a material that hardens over time, like concrete. The current "hardness" of concrete is a 
    comprehensive function of its entire history - the starting mix ratio of cement and water, the curing conditions, 
    the amount of cumulative elemental exposure - all of these historical factors represent the hereditary properties 
    of the material.
</details>

<br />

<details>
    <summary>&nbsp;<i>How does all this relate to the Caputo and Riemann-Liouville derivatives?</i></summary>&nbsp;<br/>
    These two derivatives give us an option to select how much of a system's "memory" we wish to consider in 
    the mathematical analysis of a given phenomena. <br/><br/>
    If only a portion of a system's "memory" is needed, then the Caputo derivative is used. <br/>
    If the entire system's "memory" is needed, then the Riemann-Liouville derivative is used. 
</details>

<br />

<details>
    <summary>&nbsp;<i>There are multiple coefficients in my input, but the output doesn't show the same amount. Why?</i>
    </summary>&nbsp;<br/>
    There are two reasons why this occurs:&nbsp;<br/><br/>
    <details>
        <summary>&nbsp; Fractional Derivatives of Constants</summary>&nbsp;<br/>
        For an array with multiple coefficients, the right-most coefficient represents a constant term, and the 
        fractional derivative of a constant is always zero for a Caputo fractional derivative. <br/><br/>
        If the fractional derivative of a constant is needed, use the Riemann-Liouville option.
    </details> <br />
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

<br />

<details>
    <summary>&nbsp;<i>Why are there two derivative endpoints, but only one integration endpoint?</i></summary>&nbsp;<br/>
    This is because the Caputo and Riemann-Liouville techniques treat derivatives of constant values differently.
    <br />
    <br />
    This distinction is not present in the API integration process, and both methods lead to the same results.<br/>
</details>


<br />

## Contributing

Contributions towards this project are welcome! If interested, please follow these steps:

1.) Fork the repository

2.) Create a new branch (`git checkout -b feature-branch`)

3.) Commit your changes (`git commit -m 'Add some feature'`)

4.) Push to the branch (`git push origin feature-branch`)

5.) Open a pull request

Remember to update tests if necessary and provide responses to the questions as outlined in the
pull request template.

<br />

## Changelog

### Version [1.0.0] - Released 2024-06-TBD

- Initial release with Caputo and Riemann-Liouville fractional derivative and Caputo fractional
  integration capabilities


- Includes comprehensive test coverage

<br />

## Known Issues

- When polynomial coefficients are very large, or very small, numerical precision errors may
  occur.


- The current implementation does not support multi-threading for large-scale computations.

<br />

## License

This project is licensed under the MIT License. See the LICENSE file for details.

<br />

## Acknowledgements

- <a href="https://spring.io/projects/spring-boot">Spring Boot</a>


- <a href="https://maven.apache.org/">Maven</a>


- <a href="https://commons.apache.org/proper/commons-math/">Apache Commons Math</a>


- <a href="https://openai.com/">OpenAI GPT-4o</a>

<br />

## Support

For any questions or assistance, please reach out to the project creator.
