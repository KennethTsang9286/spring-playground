package com.example.consumingrest.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.consumingrest.Object.Quote;
import com.example.consumingrest.Service.QuoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
    @Autowired
    private QuoteService quoteService;
    @Autowired
    private ExecutorService executor;

    private Quote _getQuote() {
        System.out.println("- Downloader started");
        var result = quoteService.getQuote();
        System.out.println("- Downloader end");
        return result;
    }

    @GetMapping(path = "/quote")
    public Quote getQuote() throws Exception {
        return _getQuote();
    }

    @GetMapping(path = "/quotes")
    public List<Quote> getQuotes(@RequestParam(value = "number", defaultValue = "3") Integer number) throws Exception {
        return Arrays.asList(new Integer[number])
                .parallelStream()
                .map(v -> CompletableFuture.supplyAsync(() -> _getQuote(), executor))
                .map(future -> future.join())
                .collect(Collectors.<Quote>toList());
        // Supplier<Quote> getThrow = () -> {
        // return
        // restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/ra",
        // Quote.class);
        // };

        // Supplier<Quote> getQuoteCallable = () -> {
        // return _getQuote();
        // };
        // List<CompletableFuture<Quote>> futures = new ArrayList<>();
        // IntStream.rangeClosed(1, number).forEach((x) -> {
        // futures.add(CompletableFuture.supplyAsync(getQuoteCallable, executor));
        // });
        // futures.add(CompletableFuture.supplyAsync(getThrow, executor));

        // CompletableFuture<Void> allFuturesResult = CompletableFuture
        // .allOf(futures.toArray(new CompletableFuture[futures.size()]));
        // allFuturesResult
        // .thenApply(v -> futures.stream().map(future ->
        // future.join()).collect(Collectors.<Quote>toList()));
        // // allFuturesResult.get();

        // return futures.stream().map(future ->
        // future.join()).collect(Collectors.<Quote>toList());
    }
}