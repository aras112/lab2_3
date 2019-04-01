package edu.iis.mto.similarity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import static org.hamcrest.Matchers.is;

public class SimilarityFinderTest
    {
    SequenceSearcherMock sequenceSearcher;

    @Before
    public void before()
        {
        }

    @Test
    public void simpleTestWithThisSameValue()
        {
        final boolean[] booleanValueOfNextSearchResult = {true, true};
        int[] tab1 = {1, 2};
        int[] tab2 = {1, 2};

        sequenceSearcher = new SequenceSearcherMock(booleanValueOfNextSearchResult);
        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcher);
        Assert.assertThat(similarityFinder.calculateJackardSimilarity(tab1, tab2), is(1D));
        Assert.assertThat(sequenceSearcher.getCountSearchResult(), is(2));
        }

    @Test
    public void simpleTestWithSimilarValue()
        {
        final boolean[] booleanValueOfNextSearchResult = {true, true};
        int[] tab1 = {1, 2};
        int[] tab2 = {1, 2, 3};

        sequenceSearcher = new SequenceSearcherMock(booleanValueOfNextSearchResult);
        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcher);

        Assert.assertThat(similarityFinder.calculateJackardSimilarity(tab1, tab2), is(2D / 3D));
        Assert.assertThat(sequenceSearcher.getCountSearchResult(), is(2));
        }

    @Test
    public void simpleTestWithSimilarTabNowMoreValuesInFirstTab()
        {
        final boolean[] booleanValueOfNextSearchResult = {true, true, false};
        final Integer countSearchResult = 0;
        int[] tab1 = {1, 2, 5};
        int[] tab2 = {1, 2, 3};

        sequenceSearcher = new SequenceSearcherMock(booleanValueOfNextSearchResult);
        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcher);

        Assert.assertThat(similarityFinder.calculateJackardSimilarity(tab1, tab2), is(2D / 4D));
        Assert.assertThat(sequenceSearcher.getCountSearchResult(), is(3));
        }




    private class SequenceSearcherMock implements SequenceSearcher
        {
        private boolean[] tabOfBoolForShearer;
        private Integer countSearchResult = 0;

        public Integer getCountSearchResult()
            {
            return countSearchResult;
            }

        public SequenceSearcherMock(boolean[] tabOfBoolForShearer)
            {
            this.tabOfBoolForShearer = tabOfBoolForShearer;
            }

        @Override
        public SearchResult search(int i, int[] ints)
            {
            return getMockSearchResult(tabOfBoolForShearer, countSearchResult++);
            }
        }

    private SearchResult getMockSearchResult(final boolean[] tabOfBoolForShearer,
                                             Integer countSearchResult)
        {
        return new SearchResult()
            {
            @Override
            public boolean isFound()
                {
                return tabOfBoolForShearer[countSearchResult];
                }

            @Override
            public int getPosition()
                {
                return 0;
                }
            };
        }

    }