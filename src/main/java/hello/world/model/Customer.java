package hello.world.model;

import javax.inject.Singleton;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import io.micronaut.context.annotation.Factory;
import lombok.Getter;
import lombok.Setter;

@Factory
@Singleton
@Getter
@Setter
public class Customer {

    @BsonId private ObjectId _id;
    private String firstName;
    private String lastName;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[firstName=%s, lastName=%s", firstName, lastName);
    }

    public Document toDocument() {
        return new Document()
                .append("firstName", this.firstName)
                .append("lastName", this.lastName);
    }
}
