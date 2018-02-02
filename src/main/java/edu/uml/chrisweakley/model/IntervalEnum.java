package edu.uml.chrisweakley.model;

/**
 * An enumeration specifying the frequency of stock quotes to be returned per trading day
 * within a given range of dates.
 * <code>OPEN_CLOSE</code> two quotes per day trading day, one for the opening stock price and
 * one for the closing price, 9:30 AM and 4:00 PM
 * <code>HOURLY</code> a quote every hour of each trading day: 9:30 AM open, 10:30 AM, 11:30 AM
 * 12:30 PM, 1:30 PM, 2:30 PM, 3:30 PM and the closing price at 4:00 PM
 * <code>MIDDAY</code> a single quote per trading day, 12:45 PM
 */
public enum IntervalEnum {
    OPEN_CLOSE, MIDDAY, HOURLY
}

