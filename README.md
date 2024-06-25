<!-- @formatter:off -->
# Fractional Calculus Computation API

This is a Java-based API that can produce expressions for derivatives or integrals of
user-submitted polynomial expressions using integer or fractional operational orders.

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

The derivative of a constant term using the Riemann-Liouville method does 
<i><strong>not</strong></i> result in zero. It treats constants in the expression as 
coefficients of 0th-power variables (e.g. 1x⁰ instead of just 1).

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

<br />

## FAQ

<details>
<summary>&nbsp;<i>What are fractional derivatives and fractional integrals?
</i></summary>&nbsp;<br/>
  
> These operations are extensions of the traditional integer-order calculus operations.

</details>

<br />

<details>
<summary>&nbsp;<i>What is the physical interpretation of a fractional derivative?
</i></summary>&nbsp;<br/>

> This can represent systems where rates of change are influenced by the history of physical
> processes involved, which may include unique features like non-locality, memory effects, or 
> complex dynamics.
 
</details>

<br />

<details>
<summary>&nbsp;<i>What about the physical interpretation of a fractional integral?
</i></summary>&nbsp;<br/>
  
> This can represent processes with long-range time-dependent correlations, where past states 
> influence the current state more gradually and over a longer period than would be captured 
> by a standard integer-order integral.

</details>

<br />

<details>
<summary>&nbsp;<i>Why is this important?</i>&nbsp;<br/></summary>&nbsp;<br/>

> This type of analytical technique is particularly useful for investigating or modeling  
> physical phenomena that exhibit memory effects or hereditary properties in its behavior.

</details>

<br />

<details>
<summary>&nbsp;<i>What is meant by "memory effects"?</i>
</summary>&nbsp;<br/>

> "Memory effects" refers to how a system's <i><b>recent</b></i> past influences its present 
> behavior. <br/> 
> In other words, the system "remembers" its recent history. <br/><br/>
> For example, consider a rubber band that's been stretched and released multiple times.<br /> 
> The current "stretchiness" of the rubber band not only depends on how it's being stretched 
> right now,<br /> but <i><b>also</b></i> on how it was stretched recently.

</details>

<br />

<details>
<summary>&nbsp;<i>What about "hereditary properties"?</i></summary>&nbsp;<br/>

> "Hereditary properties" refers to the characteristics of a system that depend on its 
> <i><b>entire</b></i> history. 
> <br />
> 
> As an example, consider a material that hardens over time, like concrete. <br /> The current 
> "hardness" of concrete is a comprehensive function of its entire history - the starting mix 
> ratio of cement and water, <br /> the curing conditions, the amount of cumulative elemental 
> exposure - all of these historical factors represent the hereditary <br /> properties of the
> material.

</details>

<br />

<details>
<summary>&nbsp;<i>There are multiple coefficients in my input, but the output doesn't show 
the same amount. Why?</i>
</summary>&nbsp;<br/>
  
> There are two reasons why this occurs:&nbsp;<br/>
>     
> 1.) For an array with multiple coefficients, the right-most coefficient represents a 
> constant term, and the fractional derivative of a constant is always zero for a Caputo 
> derivative. If the fractional derivative of a constant is needed, use the 
> Riemann-Liouville option.
> <br /><br />
> 
> 2.) For a Caputo derivative, if the exponent value of the term minus the order value is a 
> negative number, then that term's calculation will be omitted from the result. This is due to 
> the way in which the Caputo derivative is designed to handle "well-behaved" finite functions. 
> <br />
> Consider the following example where $f(x) = x$ and $\alpha = 2$: <br/><br/>
> ${}^{C} D^{2}{\text{&nbsp;}}[x] = \dfrac{\Gamma(2)}{\Gamma(2-2)}x^{1-2} = \dfrac{1}{\Gamma(0)}x^{-1}$
> <br/><br/>
> Since $\Gamma(0)$ is undefined, this result would be omitted from the output expression. 
> <br/>
> This applies to negative values of the gamma function as well.

</details>

<br />

<details>
<summary>&nbsp;<i>Why are there two derivative endpoints, but only one integration endpoint?</i>
</summary>&nbsp;<br/>
  
> This is because the Caputo and Riemann-Liouville techniques treat derivatives of constant 
> values differently.
> <br />
> 
> That distinction is not present in the API integration process, and both methods lead to the 
> same results. <br /> To avoid unnecessary code duplication, only the Caputo integral endpoint
> is provided.

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

### Version [1.0.0] - Released 2024-06-25

- Initial release with Caputo and Riemann-Liouville fractional derivative and Caputo fractional
  integration capabilities.


- Includes comprehensive (99%) test coverage.

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
