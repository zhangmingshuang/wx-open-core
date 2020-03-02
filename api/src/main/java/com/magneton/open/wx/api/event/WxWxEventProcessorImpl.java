package com.magneton.open.wx.api.event;

import com.magneton.service.core.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangmingshuang
 * @since 2019/9/6
 */
public class WxWxEventProcessorImpl<T> implements WxEventProcessor<T> {

    private final Map<String, Map<String, EventHandler<T>>> eventMap = new HashMap<>();

    public void setClickEvents(List<EventHandler> events) {
        synchronized (eventMap) {
            for (EventHandler event : events) {
                EventCondition eventCondition
                    = event.getClass().getAnnotation(EventCondition.class);
                if (eventCondition == null) {
                    return;
                }
                String eventType = eventCondition.event();
                String eventKey = eventCondition.eventKey();

                Map<String, EventHandler<T>> eventHandlers = eventMap.get(eventType);
                if (eventHandlers == null) {
                    eventHandlers = new ConcurrentHashMap<>(2);
                }
                EventHandler exist = eventHandlers.put(eventKey, event);
                if (exist != null) {
                    throw new RuntimeException("重复的事件配置，"
                        + exist.getClass() + ", " + event.getClass());
                }
                eventMap.put(eventType, eventHandlers);
            }
        }
    }

    @Override
    public EventHandler<T> getEvent(String event, String eventKey) {
        if (StringUtil.isEmpty(event)) {
            return null;
        }
        Map<String, EventHandler<T>> eventHandlers = eventMap.get(event);
        if (eventHandlers == null) {
            return null;
        }
        return eventHandlers.get(eventKey);
    }
}
