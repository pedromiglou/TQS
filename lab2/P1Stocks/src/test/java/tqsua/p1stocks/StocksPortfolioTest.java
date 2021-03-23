package tqsua.p1stocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    // 1. Prepare a mock to substitute the remote service (@Mock annotation)
    @Mock
    private IStockMarket market;

    // 2. Create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance.
    @InjectMocks
    private StocksPortfolio stocksPortfolio;

    @Test
    void getTotalValue() {
        // 3. Load the mock with the proper expectations (when...thenReturn)
        stocksPortfolio.addStock( new Stock("EDP", 10) );
        stocksPortfolio.addStock( new Stock("TAP", 5) );
        when(market.getPrice("EDP")).thenReturn(15.0);
        when(market.getPrice("TAP")).thenReturn(5.0);

        // 4. Execute the test (use the service in the SuT)
        double total = 15*10 + 5*5;
        //assertEquals(total, stocksPortfolio.getTotalValue());

        //assertion with Hamcrest library
        assertThat(stocksPortfolio.getTotalValue(), is(total));

        // 5. Verify the result (assert) and the use of the mock (verify)
        verify(market, times(2)).getPrice(anyString());
    }
}