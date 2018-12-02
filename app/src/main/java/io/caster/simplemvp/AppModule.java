package io.caster.simplemvp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.caster.simplemvp.presentation.UserPresenter;
import io.caster.simplemvp.presentation.UserPresenterImpl;
import io.caster.simplemvp.repository.InMemoryUserRepositoryImpl;
import io.caster.simplemvp.repository.UserRepository;

@Module
public class AppModule {
    @Provides @Singleton
    public UserRepository provideUserRepository() {
        return new InMemoryUserRepositoryImpl();
    }

    @Provides
    public UserPresenter provideUserPresenter(UserRepository userRepository) {
        return new UserPresenterImpl(userRepository);
    }
}