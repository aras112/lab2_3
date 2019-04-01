package edu.iis.mto.similarity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import static org.hamcrest.Matchers.is;

public class SimilarityFinderTest
    {
    SequenceSearcher sequenceSearcher;
    SearchResult searchResult;

    @Before
    public void before()
        {

        }


    private SequenceSearcher getMockSearcher(final boolean[] tabOfBoolForShearer)
        {
        return new SequenceSearcher()
            {

            private Integer countSearchResult = 0;

            public Integer getCountSearchResult()
                {
                return countSearchResult;
                }

            public void setCountSearchResult(Integer countSearchResult)
                {
                this.countSearchResult = countSearchResult;
                }

            @Override
            public SearchResult search(int i, int[] ints)
                {
                return getMockSearchResult(tabOfBoolForShearer, countSearchResult++);
                }


            };
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