package bookshelf.repository;

import org.junit.After;

/**
 * Created by xlo on 2015/12/16.
 * it's the repository test
 */
public class RepositoryTest {

    @After
    public void tearDown() {
        BookRepository.reset();
    }

}