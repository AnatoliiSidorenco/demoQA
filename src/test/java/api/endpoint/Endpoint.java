package api.endpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum Endpoint {
    REGISTER_USER("/Account/v1/User"),
    DELETE_USER("/Account/v1/User/{UUID}"),
    GET_USER_DATA("/Account/v1/User/{UUID}"),

    ALL_BOOKS_IN_STORE("/BookStore/v1/Books"),
    ADD_LIST_OF_BOOKS("/BookStore/v1/Books"),
    DELETE_LIST_OF_BOOKS("/BookStore/v1/Books"),
    GET_BOOK_DATA("/BookStore/v1/Book"),
    DELETE_BOOK("/BookStore/v1/Book"),
    UPDATE_BOOK("/BookStore/v1/Books/{ISBN}");

    private final String value;
}
