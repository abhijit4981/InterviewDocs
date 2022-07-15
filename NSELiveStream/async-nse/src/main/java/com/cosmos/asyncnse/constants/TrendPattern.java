package com.cosmos.asyncnse.constants;
/*
UpSide=This is when today's HighLower is greater than yesterdays HigherHigh. Good for all kinds of trading
DownSide=This is when today's LowerHigh is lesser than yesterdays HighLow. Good for Option trading
SideWays=Stays between Resistence and Support zone.Not good for Option trading but can be used for Intraday.

Resistence=Sellers Zone
Support=Buyers Zone
 */
public enum TrendPattern {
    UpSide,DownSide,SideWays
}
