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

This project is in active development with a current version of 2.0.0.  
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
- **AssertJ**: Version 3.24.2
- **Awaitility**: Version 4.2.1
- **JsonPath**: Version 2.9.0
- **JSONassert**: Version 1.5.1
- **Hamcrest**: Version 2.2
- **XMLUnit**: Version 2.9.1

### Libraries

- **Apache Commons Math**: Version 3.6.1
- **Jackson Databind**: Version 2.15.4
- **Logback**: Version 1.4.14
- **SLF4J**: Version 2.0.13
- **Hibernate Validator**: Version 8.0.1.Final
- **Lombok**: Version 1.18.32

### Additional Tools

- **JaCoCo**: Version 0.8.12
- **SnakeYAML**: Version 2.2
- **Jakarta Annotation API**: Version 2.1.1
- **Jakarta XML Bind API**: Version 4.0.2
- **Jakarta Activation API**: Version 2.1.3

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

<strong>Fractional Integral</strong>  
HTTP Verb: POST  
Endpoint URL: `/fractional-calculus-computation-api/integral`  
Returns the closed-form expression of the fractional integral.

<br />

All endpoints use the following request body format:

```json
{
  "polynomialExpression": "",
  "order": ,
  "precision": 
}
```

<br />

|       Parameter        |  Type   |                      Description                       | Required? |
|:----------------------:|:-------:|:------------------------------------------------------:|:---------:|
| `polynomialExpression` | String  | The polynomial expression to perform an operation on.  |    Yes    |
|        `order`         | Double  |        The degree of the endpoint's operation.         |    Yes    |
|      `precision`       | Integer | The number of decimal places each term should display. |    Yes    |

<br />

The API response output is also the same for all endpoints: 

```json
{
  "expression": ""
}
```

|  Parameter   |  Type  |                                    Description                                     |
|:------------:|:------:|:----------------------------------------------------------------------------------:|
| `expression` | String | The closed-form expression of the derivative or integral of `polynomialExpression` |

<br />

## Examples

<details>
<summary>
0.35724th Caputo Fractional Derivative of $4.27x^2 + 2.016x + 1$
</summary>
  
<br />

<table>
<tr>
<td>

Input:

```json
{
  "coefficients": "4.27x^2 + 2.016x + 1",
  "order": 0.35724,
  "precision": 2
}
```

<br />

API Output:

```json
{
  "expression": "5.78x^1.64276 + 2.24x^0.64276"
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
3.14159th Riemann-Liouville Fractional Derivative of $-3x^(2.12) + 2x^(1.114) + 1$
</summary>
  
<br />

<table>
<tr>
<td>

Input:

```json
{
  "coefficients": "3x^(2.12) + 2x^(1.114) + 1",
  "order": 3.14159,
  "precision": 3
}
```

<br />

API Output:

```json
{
  "expression": "0.143x^-1.02159 + 0.059x^-2.02759 - 0.313x^-3.14159"
}
```

Be aware that the Riemann-Liouville derivative of a constant value does 
<i><strong>not</strong></i> result in zero. <br /> It treats constants in the expression as 
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
  "coefficients": "3x^3 - x + 12",
  "order": 1.79,
  "precision": 5
}
```

<br />

Output:

```json
{
  "expression": "0.21376x^4.79 - 0.21559x^2.79 + 7.21807x^1.79 + C"
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
> "hardness" of concrete is a comprehensive function of its entire history - the initial
> ratio of cement and water, the curing conditions, the amount of cumulative elemental 
> exposure - all of these historical factors represent the hereditary properties of the
> material.

</details>

<br />

<details>
<summary>&nbsp;<i>Why are there two derivative endpoints, but only one integration endpoint?</i>
</summary>&nbsp;<br/>
  
> This is because the Caputo and Riemann-Liouville techniques treat derivatives of constant 
> values differently.<br />  
> 
> Caputo derivatives of constants are always zero.  
> Riemann-Liouville derivatives of constants are not zero.
> <br />
> 
> That distinction is not present in the API integration process and both methods lead to the 
> same results. <br /> Therefore, to avoid code duplication, only one integration endpoint
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

### Version [2.0.0] - Released 2024-06-30

- Endpoints now accept strings of polynomial expressions instead of coefficient arrays.


- Exponents can be encapsulated using parentheses or brackets in the input string.


- Decimal place accuracy is now controlled by the user in the endpoint request.


- Fixed an issue where outputs were displaying as strings instead of strings in a JSON object.


- Vastly expanded logging capability using SLF4J.


- Modified the integration endpoint to simply `/integral` for brevity. 

<br />

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

- <a href="https://www.baeldung.com/">Baeldung</a>

- <a href="https://www.slf4j.org/">SLF4J</a>

- <a href="https://site.mockito.org/">Mockito</a>

- <a href="https://logback.qos.ch/">Logback</a>

<br />

## Support

For any questions or assistance, please reach out to the project creator.   


