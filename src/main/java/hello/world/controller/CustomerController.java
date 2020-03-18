package hello.world.controller;

import java.util.List;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import hello.world.model.Customer;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Controller("/")
@Slf4j
public class CustomerController {
    
    private final MongoDatabase database;
    private final MongoCollection<Customer> collection;

    public CustomerController(final MongoClient mongoClient) {
        this.database = mongoClient.getDatabase("micronaut-test");
        this.collection = database.getCollection("customers", Customer.class);
    }

    @Get(value="/customers")
    public Single<List<Customer>> getCustomers() {
        return Flowable.fromPublisher(collection.find()).toList();
    }

    @Get(value="/customer/{firstName}")
    public Single<Customer> getCustomer(@PathVariable final String firstName) {
        return Single.fromPublisher(collection.find(eq("firstName", firstName)));
    }

    @Post(value="/customer")
    public Single<Customer> save(@Body final Customer customer) {
        return Single.fromPublisher(collection.insertOne(customer)).map(success -> customer);
    }

    @Delete(value="/customer/{firstName}")
    public Single<Customer> delete(@PathVariable final String firstName) {
        return Single.fromPublisher(collection.findOneAndDelete(eq("firstName", firstName)));
    }

}
