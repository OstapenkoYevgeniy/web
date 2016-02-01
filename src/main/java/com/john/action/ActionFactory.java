package com.john.action;

import com.john.property.AppPropertyManager;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActionFactory {
    public static Map<String, Action> actions;

    public static Action getAction(String action) throws ActionException {
        if (actions == null) {
            init();
        }
        return actions.get(action);
    }

    private static void init() throws ActionException {
        synchronized (ActionFactory.class) {
            String packageAction;
            actions = new HashMap<>();
            try {
                packageAction = AppPropertyManager.getInstance().getProperty("path.package.action");
            } catch (Exception e) {
                throw new ActionException(e);
            }

            Reflections reflections = new Reflections(packageAction);
            Set<Class<? extends Action>> subTypes =
                    reflections.getSubTypesOf(Action.class);

            for (Class<? extends Action> subType : subTypes) {
                try {
                    actions.put(subType.getSimpleName(), subType.newInstance());
                } catch (Exception e) {
                    throw new ActionException(e);
                }
            }
        }
    }
}
