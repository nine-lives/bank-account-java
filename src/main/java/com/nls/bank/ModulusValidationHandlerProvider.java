package com.nls.bank;

import com.nls.bank.handlers.Exception10Handler;
import com.nls.bank.handlers.Exception14Handler;
import com.nls.bank.handlers.Exception1Handler;
import com.nls.bank.handlers.Exception2Handler;
import com.nls.bank.handlers.Exception3Handler;
import com.nls.bank.handlers.Exception4Handler;
import com.nls.bank.handlers.Exception5Handler;
import com.nls.bank.handlers.Exception6Handler;
import com.nls.bank.handlers.Exception7Handler;
import com.nls.bank.handlers.Exception8Handler;
import com.nls.bank.handlers.Exception9Handler;
import com.nls.bank.handlers.ModulusAndHandler;
import com.nls.bank.handlers.ModulusOrHandler;

import java.util.HashMap;
import java.util.Map;

public final class ModulusValidationHandlerProvider {
    private static final ModulusValidationHandlerProvider DEFAULT_INSTANCE = new ModulusValidationHandlerProvider(
            new ModulusAndHandler());

    static {
        DEFAULT_INSTANCE.register(1, new Exception1Handler());
        DEFAULT_INSTANCE.register(2, new Exception2Handler());
        DEFAULT_INSTANCE.register(3, new Exception3Handler());
        DEFAULT_INSTANCE.register(4, new Exception4Handler());
        DEFAULT_INSTANCE.register(5, new Exception5Handler());
        DEFAULT_INSTANCE.register(6, new Exception6Handler());
        DEFAULT_INSTANCE.register(7, new Exception7Handler());
        DEFAULT_INSTANCE.register(8, new Exception8Handler());
        DEFAULT_INSTANCE.register(9, new Exception9Handler());
        DEFAULT_INSTANCE.register(10, new Exception10Handler());
        DEFAULT_INSTANCE.register(12, new ModulusOrHandler());
        DEFAULT_INSTANCE.register(13, new ModulusOrHandler());
        DEFAULT_INSTANCE.register(14, new Exception14Handler());
    }

    private final Map<Integer, ModulusValidationHandler> handlers = new HashMap<>();
    private ModulusValidationHandler defaultHandler = new ModulusAndHandler();

    public ModulusValidationHandlerProvider(ModulusValidationHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    public ModulusValidationHandler getDefaultHandler() {
        return defaultHandler;
    }

    public ModulusValidationHandler get(ModulusRule rule) {
        return handlers.getOrDefault(rule.getException().orElse(null), defaultHandler);
    }

    public ModulusValidationHandler get(Integer exception) {
        return handlers.get(exception);
    }

    public void register(int exception, ModulusValidationHandler handler) {
        handlers.put(exception, handler);
    }

    public static ModulusValidationHandlerProvider getInstance() {
        return DEFAULT_INSTANCE;
    }

}
