package garlinghomework

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Test extends Simulation {

	val httpProtocol = http
		.baseUrl("https://demo.nopcommerce.com")
		.inferHtmlResources()
		.acceptHeader("image/webp,*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("uk-UA,uk;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0")
		.disableFollowRedirect

	val search = "laptop"

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map("Accept" -> "*/*")

	val headers_13 = Map(
		"Accept" -> "*/*",
		"Content-Type" -> "text/plain",
		"Origin" -> "https://demo.nopcommerce.com")

	val headers_22 = Map(
		"Accept" -> "*/*",
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"Origin" -> "https://demo.nopcommerce.com",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_23 = Map(
		"Accept" -> "*/*",
		"Content-Type" -> "text/plain;charset=UTF-8",
		"Origin" -> "null")

	val headers_28 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Content-Type" -> "multipart/form-data; boundary=---------------------------12582612746783460881816210841",
		"Origin" -> "https://demo.nopcommerce.com",
		"Upgrade-Insecure-Requests" -> "1")

    val uri1 = "https://www.google-analytics.com"
    val uri2 = "https://api-public.addthis.com/url/shares.json"
    val uri3 = "https://v1.addthisedge.com/live/boost/nopsolutions/_ate.track.config_resp"
    val uri5 = "https://m.addthis.com/live/red_lojson"

	val users = 1;

	val scn = scenario("Test")
		// open site
		.exec(http("start")
			.get("/")
				.check(regex("<title>(.+?)</title>").is("nopCommerce demo store"))
			.headers(headers_0))
		.pause(8)
		// search
		.exec(http("search for " + search)
			.get("/search?q=" + search)
			.check(status.is(200))
			.headers(headers_0))
		.pause(15)
		// pdp
		.exec(http("open product")
			.get("/asus-n551jk-xo076h-laptop")
			.check(status.is(200))
			.headers(headers_0))
		.pause(10)
		// addToCart
		.exec(http("add to cart")
			.post("/addproducttocart/details/5/1")
			.headers(headers_22)
			.formParam("addtocart_5.EnteredQuantity", "1")
			.formParam("CountryId", "0")
			.formParam("StateProvinceId", "0")
			.formParam("ZipPostalCode", "")
			.formParam("__RequestVerificationToken", "CfDJ8NJzpPdWJDZGtf_4GVVpZ2kC87dFCBKnH7EUjWrH2SKHEtUYf1xBsTGTB1f3n_523UDgyfDqHvL4FmtivJdTMkQhQQpH0CjqZMaQSF-zNQgBjTW1Ep4S1wm2ZCuJaRmWCYNnWS0Y9LOGa5-vNouZg3c"))
		.pause(16)
		// open cart
		.exec(http("post json")
			.post(uri5 + "/100eng.json?sh=2237&ph=4078&ivh=759&dt=28522&pdt=1732&ict=&pct=2&perf=widget%7C1732%7C93%2Clojson%7C2074%7C517%2Csh%7C2081%7C44&rndr=render_toolbox%7C2472&cmenu=null&ppd=0&ppl=0&fbe=&xmv=0&xms=0&xmlc=0&jsfw=jquery%2Cgoogleanalytics&jsfwv=jquery-3.4.1%2Cgoogleanalytics-analytics.js&al=men&scr=0&scv=0&apiu=0&ba=3&sid=5f944d5320f9f667&rev=v8.28.7-wp&pub=nopsolutions&dp=demo.nopcommerce.com&fp=asus-n551jk-xo076h-laptop&pfm=0&icns=addthis%2Cfacebook%2Ctwitter%2Cprint%2Cemail")
			.headers(headers_23)
			.resources(http("open cart")
			.get("/cart")
			.headers(headers_0),
            http("shipping options")
			.post("/shoppingcart/selectshippingoption")
			.headers(headers_22)
			.formParam("itemquantity11255", "1")
			.formParam("CountryId", "0")
			.formParam("StateProvinceId", "0")
			.formParam("ZipPostalCode", "")
			.formParam("checkout_attribute_1", "1")
			.formParam("discountcouponcode", "")
			.formParam("giftcardcouponcode", "")
			.formParam("__RequestVerificationToken", "CfDJ8NJzpPdWJDZGtf_4GVVpZ2nsw0spFp39EYWut5MEfZ1yFy63ETsaR-Z8kJ8ZVgUsAgG4SuO63mfco0hAMsu4rEi0U7IusEm3tGZbgf1WMjs5lrZFHFSgWJkcVmw_k7-jlUO4jCqBB9FkgONQupZ4AUY"),
            http("request_27")
			.post("/shoppingcart/checkoutattributechange?isEditable=True")
			.headers(headers_22)
			.formParam("itemquantity11255", "1")
			.formParam("CountryId", "0")
			.formParam("StateProvinceId", "0")
			.formParam("ZipPostalCode", "")
			.formParam("checkout_attribute_1", "1")
			.formParam("discountcouponcode", "")
			.formParam("giftcardcouponcode", "")
			.formParam("__RequestVerificationToken", "CfDJ8NJzpPdWJDZGtf_4GVVpZ2nsw0spFp39EYWut5MEfZ1yFy63ETsaR-Z8kJ8ZVgUsAgG4SuO63mfco0hAMsu4rEi0U7IusEm3tGZbgf1WMjs5lrZFHFSgWJkcVmw_k7-jlUO4jCqBB9FkgONQupZ4AUY")))
		.pause(14)
		// continue shopping
		.exec(http("go to cart")
			.post("/cart")
			.headers(headers_28)
			.body(RawFileBody("garlinghomework/test/0028_request.dat")))

	setUp(scn.inject(rampUsers(users) during (15 seconds))).assertions(
		global.successfulRequests.percent.gt(80)
	).protocols(httpProtocol)
}