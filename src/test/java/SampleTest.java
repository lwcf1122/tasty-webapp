import org.testng.annotations.Test;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by flao on 5/21/15.
 */
@Slf4j
public class SampleTest {

    @Test
    public void insertionSortingTest() {
        Integer [] arrayA = {5, 2, 4, 6, 1, 3};
        //2, 5, 4, 6, 1, 3
        //2, 4, 5, 6, 1, 3
        //
        for (int j = 1; j < arrayA.length; j++) {
            Integer key = arrayA[j];
            int i = j - 1;
            while (i >= 0 && arrayA[i] > key) {
                arrayA[i + 1] = arrayA[i];
                i = i - 1;
            }
            arrayA[i + 1] = key;
        }
        log.info("sorted array:" + Arrays.asList(arrayA).toString());
    }

}
