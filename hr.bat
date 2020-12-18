call mvn -B -s settings.xml -DskipTests=true clean package
call java -Dspring.profiles.active="heroku" -DDATABASE_URL="postgres://User:123@localhost:5432/you-promocode-bot" -jar target/dependency/webapp-runner.jar target/*.war
