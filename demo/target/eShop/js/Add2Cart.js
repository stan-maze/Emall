const addToCartForms = document.querySelectorAll(".add-to-cart-form");

addToCartForms.forEach((form) => {
  form.addEventListener("submit", (event) => {
    event.preventDefault();

    const quantity = form.elements["quantity"].value;
    const productId = form.elements["productId"].value;

    fetch(`add-to-cart?productId=${productId}&quantity=${quantity}`)
      .then((response) => response.json())
      .then((data) => {
        if (data.success) {
          alert("Product added to cart successfully!");
        } else {
          alert(`Failed to add product to cart: ${data.errorMessage}`);
        }
      })
      .catch((error) => {
        console.error(error);
        alert("An error occurred while adding the product to cart.");
      });
  });
});
