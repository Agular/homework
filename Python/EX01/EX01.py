def convert_dates_to_coconuts(count):
    dates = count
    papayas = 0
    bananas = 0
    coconuts = 0
    trade_count = 0

    papayas = dates//7
    trade_count+=dates//7
    
    bananas= (papayas//5)*2
    trade_count+= papayas//5

    coconuts = bananas//3
    trade_count+=bananas//3

    if(trade_count>=0):
        return trade_count
    else: return 0
