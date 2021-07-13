package br.com.elo.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FixtureCleaner {
    @Autowired
    private List<Fixture> fixtures;

    public void cleanup() {
        this.fixtures.forEach(Fixture::cleanup);
    }
}
