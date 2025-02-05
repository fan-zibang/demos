package cn.fanzibang.config;

import cn.fanzibang.api.IUserApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class Retrofit2Config {

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Bean
    public IUserApiService userApiService(Retrofit retrofit) {
        return retrofit.create(IUserApiService.class);
    }

}
