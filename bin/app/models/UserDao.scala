package models

import javax.inject.Inject
import org.bouncycastle.crypto.generators.SCrypt;

@javax.inject.Singleton
class UserDao @Inject()() {

  val SALT: String = "abc123";

  // DifficultyFactor
  // These should be powers of 2
  val cpu: Int = 8;
  val memory: Int = 8;
  val parallelism: Int = 8;
  val outputLength: Int = 32;

  def hashPsw(plainText: String): String = {
      val hash = SCrypt.generate(plainText.getBytes(), SALT.getBytes(), cpu, memory, parallelism, outputLength);
      return new String(hash);
  }

  def checkPsw(plainText: String, hashedStr: String):  Boolean = {
      val hash = SCrypt.generate(plainText.getBytes(), SALT.getBytes(), cpu, memory, parallelism, outputLength);
      val stored = new String(hash);
      return stored.equals(hashedStr);
  }

  def lookupUser(u: User): Boolean = {
      val plainText = u.password;
      val stored = hashPsw("pass");
      val isMatch = checkPsw(plainText, stored);

      if (u.username == "user" && isMatch) true else false
  }

}
