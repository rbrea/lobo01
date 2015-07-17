package com.icetea.manager.pagodiario.api.dto.exception;

public enum ErrorType {
    CLIENT_NOT_FOUND(1, "Cliente no encontrado"),
    TRADER_NOT_FOUND(8, "Vendedor no encontrado"),
    CLIENT_REQUIRED(2, "Cliente requerido"),
    VALIDATION_ERRORS(3, "Errores de validacion"),
    UNKNOWN_ERROR(4, "Error desconocido"),
    PRODUCT_REQUIRED(5, "producto requerido"),
    PRODUCT_NOT_FOUND(6, "producto no encontrado"),
    BILL_NOT_FOUND(7, "Factura no encontrada"),
    BILL_REQUIRED(8, "Factura requerida");

    private final Integer id;
    private final String text;

    private ErrorType(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return this.id;
    }

    public static Integer getId(ErrorType type) {
        for (ErrorType t : values()) {
            if (t == type) {
                return t.getId();
            }
        }

        return -1;
    }

    public static ErrorType valueOf(Integer id) {
        for (ErrorType t : values()) {
            if (t.getId() == id) {
                return t;
            }
        }

        return null;
    }

    public String getText() {
        return this.text;
    }

}
