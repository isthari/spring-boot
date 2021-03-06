/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.propertyvalidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

@SpringBootApplication
@EnableConfigurationProperties
public class SamplePropertyValidationApplication {

	@Bean
	public Validator configurationPropertiesValidator() {
		return new SamplePropertiesValidator();
	}

	@Service
	@Profile("app")
	static class Startup implements CommandLineRunner {

		@Autowired
		private SampleProperties properties;

		@Override
		public void run(String... args) {
			System.out.println("=========================================");
			System.out.println("Sample host: " + this.properties.getHost());
			System.out.println("Sample port: " + this.properties.getPort());
			System.out.println("=========================================");
		}
	}

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(SamplePropertyValidationApplication.class)
				.profiles("app").run(args);
	}

}
