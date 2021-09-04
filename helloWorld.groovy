@RestController
class SpringBootWebApplication {

    @RequestMapping("/")
    String hello() {
        "Hello world container. You are able to reach the Docker host!\n"
    }
}
