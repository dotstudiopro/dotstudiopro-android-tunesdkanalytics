package com.dotstudioz.dotstudioPRO.analytics.TuneSDK;

import android.content.Context;

import com.tune.ITune;
import com.tune.Tune;
import com.tune.TuneEvent;

public class TuneAnalyticsEventsService {

    public String ADVERTISING_ID_CLIENT;
    public String CLIENT_TOKEN;
    public String userEmailId;

    private TuneAnalyticsEventsService() { }

    private static TuneAnalyticsEventsService mInstance = new TuneAnalyticsEventsService();
    public static synchronized TuneAnalyticsEventsService getInstance() {
        if(mInstance == null)
            mInstance = new TuneAnalyticsEventsService();
        return mInstance;
    }

    public void initialize(Context ctx, String advID, String clientToken, String userEmail, String tuneAdvId, String tuneConvKey) {
        ADVERTISING_ID_CLIENT = advID;
        CLIENT_TOKEN = clientToken;
        userEmailId = userEmail;

        // Initialize TMC
        Tune.init(ctx, tuneAdvId, tuneConvKey);
    }

    public void saveAnalyticsEvent(String eventType, String contentType) {
        try {
            TuneEvent tuneEvent = new TuneEvent(eventType);
            if (ADVERTISING_ID_CLIENT != null && ADVERTISING_ID_CLIENT.length() > 0) {
                tuneEvent.withAdvertiserRefId(ADVERTISING_ID_CLIENT);
            }
            if (contentType != null && contentType.length() > 0)
                tuneEvent.withContentType(contentType);
            ITune tune = Tune.getInstance();
            try {
                if (CLIENT_TOKEN != null && CLIENT_TOKEN.length() > 0 &&
                        userEmailId != null && userEmailId.length() > 0) {
                    tune.setUserEmail(userEmailId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            tune.measureEvent(tuneEvent);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void savePreDefinedAnalyticsEvent(String eventType) {
        try {
            TuneEvent tuneEvent = new TuneEvent(eventType);
            if (ADVERTISING_ID_CLIENT != null && ADVERTISING_ID_CLIENT.length() > 0) {
                tuneEvent.withAdvertiserRefId(ADVERTISING_ID_CLIENT);
            }
            ITune tune = Tune.getInstance();
            try {
                if (CLIENT_TOKEN != null && CLIENT_TOKEN.length() > 0 &&
                        userEmailId != null && userEmailId.length() > 0) {
                    tune.setUserEmail(userEmailId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            tune.measureEvent(tuneEvent);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCheckOutInitiatedAnalyticsEvent(String eventType, String money) {
        try {
            TuneEvent tuneEvent = new TuneEvent(eventType);
            if (ADVERTISING_ID_CLIENT != null && ADVERTISING_ID_CLIENT.length() > 0) {
                tuneEvent.withAdvertiserRefId(ADVERTISING_ID_CLIENT);
            }
            if(money != null && money.length() > 0) {
                tuneEvent.withRevenue(Double.parseDouble(money));
            }
            ITune tune = Tune.getInstance();
            try {
                if (CLIENT_TOKEN != null && CLIENT_TOKEN.length() > 0 &&
                        userEmailId != null && userEmailId.length() > 0) {
                    tune.setUserEmail(userEmailId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            tune.measureEvent(tuneEvent);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
