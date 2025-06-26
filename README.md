##  Cart API – Overview

A simple shopping cart feature built with Spring Boot.

###  Endpoints

- **Create Cart**  
  `POST /carts`  
  ➤ Creates a new empty cart and returns its ID.

- **Add Product to Cart**  
  `POST /carts/{cartId}/items`  
  ➤ Adds a product to the cart or increases quantity if already in cart.

- **Get Cart**  
  `GET /carts/{cartId}`  
  ➤ Returns all items in the cart including total prices.

- **Update Item Quantity**  
  `PUT /carts/{cartId}/items/{productId}`  
  ➤ Updates the quantity of a product already in the cart.

- **Remove Product from Cart**  
  `DELETE /carts/{cartId}/items/{productId}`  
  ➤ Removes a specific product from the cart.

- **Clear All Items from Cart**  
  `DELETE /carts/{cartId}/items`  
  ➤ Clears the entire cart.

###  Error Handling

- Returns `404 Not Found` if the cart doesn't exist.
- Returns `400 Bad Request` if the product doesn't exist.

---
![image](https://github.com/user-attachments/assets/00fd5ec7-55ec-417c-addc-a11e788c78bd)

