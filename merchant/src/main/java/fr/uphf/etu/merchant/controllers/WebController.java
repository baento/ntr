package fr.uphf.etu.merchant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.uphf.etu.gateway.wsdl.TransactionResponse;
import fr.uphf.etu.merchant.clients.TransactionClient;
import fr.uphf.etu.merchant.models.Product;
import fr.uphf.etu.merchant.payloads.CartRequest;
import fr.uphf.etu.merchant.payloads.CheckoutRequest;
import fr.uphf.etu.merchant.services.CartService;
import fr.uphf.etu.merchant.services.ProductService;

@Controller
public class WebController {

	@Autowired
	ProductService productService;

	@Autowired
	CartService cartService;

	@Autowired
	TransactionClient transactionClient;

	@RequestMapping
	public String viewIndex(Model model) {
		model.addAttribute("products", productService.getAll());

		return "index";
	}

	@RequestMapping("/cart")
	public String viewCart(Model model) {
		model.addAttribute("cart", cartService.getProducts());
		model.addAttribute("total", cartService.getTotal());

		return "cart";
	}

	@RequestMapping("/cart/checkout")
	public String viewCheckout(Model model) {
		if (cartService.getProducts().isEmpty()) {
			return "redirect:/";
		}

		return "checkout";
	}

	@PostMapping("/cart/checkout")
	public String checkoutCart(RedirectAttributes redirectAttributes, CheckoutRequest request) {
		TransactionResponse response = transactionClient.doTransaction(request.getId(), request.getCode(),
				cartService.getTotal());

		if (response.isSuccess()) {
			cartService.clear();
			redirectAttributes.addFlashAttribute("message", "Payment approved !");
			return "redirect:/";
		} else {
			redirectAttributes.addFlashAttribute("message", "Payment denied : " + response.getMessage());
			return "redirect:/cart/checkout";
		}
	}

	@PostMapping("/addToCart")
	public String addProductToCart(Model model, CartRequest.Add addRequest) {
		Product product = productService.getProduct(addRequest.getId());

		cartService.addProduct(product, addRequest.getQuantity());

		return "redirect:/";
	}

	@PostMapping("/removeFromCart")
	public String removeProductFromCart(Model model, CartRequest.Remove removeRequest) {
		Product product = productService.getProduct(removeRequest.getId());

		cartService.removeProduct(product, removeRequest.getQuantity());

		return "redirect:/cart";
	}
}