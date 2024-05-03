# Fractional Calculus Computation API (in progress)

## Overview

This is a computational API that calculates derivatives or integrals using fractional
calculus techniques based on user input.

## Background

With ordinary calculus, the number of times we differentiate or integrate a function is typically referred to as the 
_**order**_ of the operation. This order value is restricted to positive integers only.

Fractional calculus is not bound to this restriction; we are free to take the 4.687-th integral of a function, or the 
π-th derivative if we wish. This grants a much greater precision with which we can analyze the behavior of complex functions in 
the real world.

## Some Real-World Applications of Fractional Calculus

- Conservation of mass in fluid flow [Wheatcraft and Meerschaert (2008)]
- Analysis of groundwater flow [Atangana et al. (2013-2014)]
- Modeling viscoelastic dampening in polymer materials [Mainardi (2010)]
- Propagation of acoustical waves in complex media (biological tissue) [Holm & Näsholm (2011)]
- Expanding the solution of the Schrödinger equation in quantum mechanics [Bhrawy & Zaky (2017)]


<br>





(Plan on rewriting these using LaTeX):

1.) D^α(x) (e^(kx)) = k^α e^(kx)

2.) D^α(x) (sin(kx)) = k^α sin(kx + απ/2)

3.) D^α(x) x^k = Γ(1+k)/Γ(1+k-α)

For 1 and 2, k >= 0.
For 3, x >= 0, and K is an element of the positive integers (including zero).

## (Intended) Design Diagram

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

## Diagram Legend

```mermaid

flowchart TD

%% Diagram Legend Shapes %%    
    L1([" App start  "]):::start
    L2["&nbsp Program &nbsp \n Action "]:::action
    L3[/" Program Output "/]:::output
    L4[/" User Input "/]:::input
    L5{"Decision"}:::decision
    L6([" App finish "]):::finish
%% Links %%        
    L1 ~~~ L2 ~~~ L3 ~~~ L4 ~~~ L5 ~~~ L6
%% Class Colors %%
    classDef start stroke: #0f0, stroke-width: 2.5px;
    classDef finish stroke: #f00, stroke-width: 2.5px;
    classDef decision stroke: #cc5500, stroke-width: 2.5px;
    classDef action stroke: #196de3, stroke-width: 2.5px;
    classDef input stroke: #ca14de, stroke-width: 2.5px;
    classDef output stroke: #ede205 , stroke-width: 2.5px;
    classDef empty width: 0px, height: 0px;
```