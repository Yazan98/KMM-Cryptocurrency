// Generated by Dagger (https://dagger.dev).
package com.yazantarifi.coina.android;

import com.yazantarifi.coina.api.requests.ApplicationApiManager;
import com.yazantarifi.coina.database.ExchangesDataSource;
import com.yazantarifi.coina.useCases.GetExchangesUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApplicationModule_GetGetExchangesUseCaseFactory implements Factory<GetExchangesUseCase> {
  private final Provider<ApplicationApiManager> apiManagerProvider;

  private final Provider<ExchangesDataSource> databaseProvider;

  public ApplicationModule_GetGetExchangesUseCaseFactory(
      Provider<ApplicationApiManager> apiManagerProvider,
      Provider<ExchangesDataSource> databaseProvider) {
    this.apiManagerProvider = apiManagerProvider;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public GetExchangesUseCase get() {
    return getGetExchangesUseCase(apiManagerProvider.get(), databaseProvider.get());
  }

  public static ApplicationModule_GetGetExchangesUseCaseFactory create(
      Provider<ApplicationApiManager> apiManagerProvider,
      Provider<ExchangesDataSource> databaseProvider) {
    return new ApplicationModule_GetGetExchangesUseCaseFactory(apiManagerProvider, databaseProvider);
  }

  public static GetExchangesUseCase getGetExchangesUseCase(ApplicationApiManager apiManager,
      ExchangesDataSource database) {
    return Preconditions.checkNotNullFromProvides(ApplicationModule.INSTANCE.getGetExchangesUseCase(apiManager, database));
  }
}
