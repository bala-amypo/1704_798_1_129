demo
│
├── pom.xml
│
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── demo
│       │
│       │               ├── DemoApplication.java
│       │
│       │               ├── servlet
│       │               │   └── HelloServlet.java
│       │
│       │               ├── config
│       │               │   ├── JwtTokenProvider.java
│       │               │   └── SwaggerConfig.java
│       │
│       │               ├── controller
│       │               │   ├── AuthController.java
│       │               │   ├── ProductController.java
│       │               │   ├── CartController.java
│       │               │   └── BundleRuleController.java
│       │
│       │               ├── service
│       │               │   ├── AuthService.java
│       │               │   ├── ProductService.java
│       │               │   ├── CartService.java
│       │               │   ├── DiscountService.java
│       │               │   └── impl
│       │               │       ├── AuthServiceImpl.java
│       │               │       ├── ProductServiceImpl.java
│       │               │       ├── CartServiceImpl.java
│       │               │       └── DiscountServiceImpl.java
│       │
│       │               ├── repository
│       │               │   ├── UserRepository.java
│       │               │   ├── RoleRepository.java
│       │               │   ├── ProductRepository.java
│       │               │   ├── CartRepository.java
│       │               │   ├── CartItemRepository.java
│       │               │   ├── BundleRuleRepository.java
│       │               │   └── DiscountApplicationRepository.java
│       │
│       │               ├── model
│       │               │   ├── User.java
│       │               │   ├── Role.java
│       │               │   ├── Product.java
│       │               │   ├── Cart.java
│       │               │   ├── CartItem.java
│       │               │   ├── BundleRule.java
│       │               │   └── DiscountApplication.java
│       │
│       │               ├── dto
│       │               │   ├── RegisterRequest.java
│       │               │   ├── AuthRequest.java
│       │               │   └── AuthResponse.java
│       │
│       │               ├── exception
│       │               │   └── ResourceNotFoundException.java
│       │
│       │               └── security
│       │                   ├── JwtAuthenticationFilter.java
│       │                   └── SecurityConfig.java
│       │
│       └── resources
│           ├── application.properties
│           └── static
│
└── test
