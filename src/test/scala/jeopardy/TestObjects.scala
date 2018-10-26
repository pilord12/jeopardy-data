package jeopardy

import jeopardy.model._

import io.Source

object TestObjects {
  private val TEST_FILES_BASE_LOC = "src/test/resources"
  private val TEST_GAME_FILES_LOC = TEST_FILES_BASE_LOC + "/games"

  /**
    * Gets the full project path of a test game file
    * @param filename simple filename to search for
    * @return project path string leading to the filename
    */
  private def gameFileFullPath(filename: String): String = TEST_GAME_FILES_LOC + s"/$filename"

  lazy val game12Html: String = Source.fromFile(gameFileFullPath("game12.html")).mkString
  lazy val invalidGameHtml: String = Source.fromFile(gameFileFullPath("invalid_game.html")).mkString

  lazy val expectedGame12Object = JeopardyGame(
    id = "4607",
    number = Some(4607),
    firstRound = JeopardyRound(
      round = Some(JeopardyRounds.FIRST),
      categories = Vector(
        JeopardyCategory(
          title = "RECENT FICTION",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("\"The Princes of Ireland\" is vol. 1 of Edward Rutherfurd's \"Saga\" named for this capital"),
              correctAnswer = Some("Dublin"),
              dollarValue = Some(200)
            ),
            JeopardyQuestion(
              clue = Some("A song provided the title of this Michael Walsh novel featuring Isla Lund & Victor Laszlo"),
              correctAnswer = Some("As Time Goes By"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("This term for a lawyer who pulls in profits provided the title of a John Grisham book"),
              correctAnswer = Some("The Rainmaker"),
              dollarValue = Some(600)
            ),
            JeopardyQuestion(
              clue = Some("\"Empire\" is nonfiction by Niall Ferguson; this is Richard Russo's novel about small town man Miles Roby"),
              correctAnswer = Some("Empire Falls"),
              dollarValue = Some(800)
            ),
            JeopardyQuestion(
              clue = Some("In a series set in Africa, a woman named Precious Ramotswe runs this crime-solving outfit"),
              correctAnswer = Some("The No. 1 Ladies' Detective Agency"),
              dollarValue = Some(1000)
            )
          )
        ),
        JeopardyCategory(
          title = "BASEBALL ROOKIES OF THE YEAR",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("This center fielder who hit 660 homers & won 2 NL MVP awards started his pro career in the Negro Leagues"),
              correctAnswer = Some("Mays"),
              dollarValue = Some(200)
            ),
            JeopardyQuestion(
              clue = Some("He was the first Japanese player in nearly 30 years to play in the majors when he pitched for the Dodgers in 1995"),
              correctAnswer = Some("Nomo"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("This pitcher was Rookie of the Year in 1967; he won the Cy Young & a World Series ring in 1969"),
              correctAnswer = Some("Seaver"),
              dollarValue = Some(600)
            ),
            JeopardyQuestion(
              clue = Some("1956's Rookie of the Year with Cincinnati, he was the first player to be MVP in both the AL & NL"),
              correctAnswer = Some("Frank Robinson"),
              dollarValue = Some(800)
            ),
            JeopardyQuestion(
              clue = Some("Playing for the New York Mets, he was named the 1983 National League Rookie of the Year"),
              correctAnswer = Some("Darryl Strawberry"),
              dollarValue = Some(1000)
            )
          )
        ),
        JeopardyCategory(
          title = "WAXING PHILOSOPHICAL",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("This philosopher & partner of Engels took up residence in London after being expelled from Prussia in 1849"),
              correctAnswer = Some("Marx"),
              dollarValue = Some(200)
            ),
            JeopardyQuestion(
              clue = Some("Seen here, the death of this Athenian philosopher was immortalized by Jacques-Louis David in 1787"),
              correctAnswer = Some("Socrates"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("This philosopher & Sartre convened an intl. war crimes tribunal to publicize U.S. atrocities in Vietnam"),
              correctAnswer = Some("Bertrand Russell"),
              dollarValue = Some(600)
            ),
            JeopardyQuestion(
              clue = Some("In 1937 this U.S. educator headed an inquiry to hear Trotsky's rebuttal of the Moscow charges against him"),
              correctAnswer = Some("John Dewey"),
              dollarValue = Some(800)
            ),
            JeopardyQuestion(
              clue = Some("This intellectual known for his concept of the absurd classifies rebels into world deniers and world affirmers"),
              correctAnswer = Some("Albert Camus"),
              dollarValue = Some(1000)
            )
          )
        ),
        JeopardyCategory(
          title = "SWEET",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("Benjamin Eisenstadt introduced this pink-packeted product in 1957"),
              correctAnswer = Some("Sweet'N Low"),
              dollarValue = Some(200)
            ),
            JeopardyQuestion(
              clue = Some("1 1997 song by The Verve says life is this type of symphony"),
              correctAnswer = Some("a bittersweet symphony"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("They're the climbers seen here"),
              correctAnswer = Some("sweet peas"),
              dollarValue = Some(600)
            ),
            JeopardyQuestion(
              clue = Some("This Texas city is the seat of Nolan County"),
              correctAnswer = Some("Sweetwater"),
              dollarValue = Some(800)
            ),
            JeopardyQuestion(
              clue = Some("\"Albertine Disparue\", a volume in this epic work, is sometimes translated as \"The Sweet Cheat Gone\""),
              correctAnswer = Some("Remembrance of Things Past"),
              dollarValue = Some(1000)
            )
          )
        ),
        JeopardyCategory(
          title = "BALLROOM BLITZ",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("The \"Virginia\" variety of this country dance starts with partners facing each other in 2 lines"),
              correctAnswer = Some("the reel"),
              dollarValue = Some(200)
            ),
            JeopardyQuestion(
              clue = Some("A star was born when Rudolph Valentino did this Latin-American dance in \"Four Horsemen of the Apocalypse\""),
              correctAnswer = Some("a tango"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("An 18th century dance & a social ball are both called this, from a French word for \"petticoat\""),
              correctAnswer = Some("a cotillion"),
              dollarValue = Some(600)
            ),
            JeopardyQuestion(
              clue = Some("Wham's hit \"Wake Me Up Before You Go-Go\" mentions this American dance of the 1930s & '40s"),
              correctAnswer = Some("the jitterbug"),
              dollarValue = Some(800),
              isWager = true
            ),
            JeopardyQuestion(
              clue = Some("This ballroom dance may have been named for vaudeville comedian Harry"),
              correctAnswer = Some("the foxtrot"),
              dollarValue = Some(1000)
            )
          )
        ),
        JeopardyCategory(
          title = "SILENT \"P\"",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("I'm somehow sensing you'll know this word for a medium, from the Greek for \"of the soul\""),
              correctAnswer = Some("psychic"),
              dollarValue = Some(200)
            ),
            JeopardyQuestion(
              clue = Some("An extinct flying reptile from the Cretaceous Period, it had a wingspan of about 25 feet"),
              correctAnswer = Some("a pteranodon"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("11-letter term describing the era of Jimi Hendrix & Timothy Leary"),
              correctAnswer = Some("psychedelic (psychedelia accepted)"),
              dollarValue = Some(600)
            ),
            JeopardyQuestion(
              clue = Some("Any of several grouses of the genus Lagopus, having feathered feet"),
              correctAnswer = Some("a ptarmigan"),
              dollarValue = Some(800)
            ),
            JeopardyQuestion(
              clue = Some("Also called parrot fever, it can be contracted by handling sick parrots, pigeons or poultry"),
              correctAnswer = Some("psittacosis"),
              dollarValue = Some(1000)
            )
          )
        )
      )
    ),
    secondRound = JeopardyRound(
      round = Some(JeopardyRounds.SECOND),
      categories = Vector(
        JeopardyCategory(
          title = "HEIR OF THE DAG",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("He succeeded Dag Hammarskjold as U.N. Secretary-General & used a single letter before his name"),
              correctAnswer = Some("U Thant"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("Born in Ghana in 1938, this U.N. Secretary-General won a Nobel Peace Prize in 2001"),
              correctAnswer = Some("Kofi Annan"),
              dollarValue = Some(800)
            ),
            JeopardyQuestion(
              clue = Some("Serving from 1946 to 1952, this Oslo University graduate was the first U.N. Secretary-General"),
              correctAnswer = Some("Trygve Lie"),
              dollarValue = Some(1200)
            ),
            JeopardyQuestion(
              clue = Some("After his term as U.N. Secretary-General he became president of Austria in 1986"),
              correctAnswer = Some("(Kurt) Waldheim"),
              dollarValue = Some(1600)
            ),
            JeopardyQuestion(
              clue = Some("This Secretary-General accompanied Anwar Sadat on his historic trip to Israel in 1977"),
              correctAnswer = Some("(Boutros) Boutros-Ghali"),
              dollarValue = Some(2000),
              isWager = true
            )
          )
        ),
        JeopardyCategory(
          title = "BIG SCREEN BIOS",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("\"Great Balls of Fire!\""),
              correctAnswer = Some("Jerry Lee Lewis"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("\"Your Cheatin' Heart\""),
              correctAnswer = Some("Hank Williams, Sr."),
              dollarValue = Some(800)
            ),
            JeopardyQuestion(
              clue = Some("\"Bananas Is My Business\""),
              correctAnswer = Some("(Carmen) Miranda"),
              dollarValue = Some(1200)
            ),
            JeopardyQuestion(
              clue = Some("\"Man of a Thousand Faces\""),
              correctAnswer = Some("Lon Chaney, Sr."),
              dollarValue = Some(1600)
            ),
            JeopardyQuestion(
              clue = Some("\"Monster\""),
              correctAnswer = Some("Aileen Wuornos"),
              dollarValue = Some(2000)
            )
          )
        ),
        JeopardyCategory(
          title = "YOU HAD TO EXPECT SHAKESPEARE",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("This character described himself as having \"more flesh than another man, and therefore more frailty\""),
              correctAnswer = Some("Falstaff"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("About 230 years B.S. (before Shakespeare), Chaucer wrote an 8,000-line poem about this title Homeric pair"),
              correctAnswer = Some("Troilus and Criseyde"),
              dollarValue = Some(800)
            ),
            JeopardyQuestion(
              clue = Some("After being a 16th C. version of \"The Terminator\", the title guy buys the farm too at the end of this, Will's 1st tragedy"),
              correctAnswer = Some("Titus Andronicus"),
              dollarValue = Some(1200),
              isWager = true
            ),
            JeopardyQuestion(
              clue = Some("In this comedy featuring a \"fantastical Spaniard\", 4 guys take a 3-year vow of celibacy & wackiness ensues"),
              correctAnswer = Some("Love's Labour's Lost"),
              dollarValue = Some(1600)
            ),
            JeopardyQuestion(
              clue = Some("The last of the tragedies, this play deals with the conflict between Rome's patrician & plebeian classes"),
              correctAnswer = Some("Coriolanus"),
              dollarValue = Some(2000)
            )
          )
        ),
        JeopardyCategory(
          title = "GREAT MOMENTS IN HISTORY",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("The first brewery in the New World was opened in 1612 in the city that then had this \"Dutch\" name"),
              correctAnswer = Some("New Amsterdam"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("His establishment of the St. Louis Refrigerator Car Co. in 1878 enabled his beer to be distributed on a large-scale basis"),
              correctAnswer = Some("Anheuser-Busch"),
              dollarValue = Some(800)
            ),
            JeopardyQuestion(
              clue = Some("Cheers to Congress for adopting this number amendment in 1933 to deal with a drier former one"),
              correctAnswer = Some("21st"),
              dollarValue = Some(1200)
            ),
            JeopardyQuestion(
              clue = Some("In the 1890's William Painter invented the top, seen here, whose projections got it this \"regal\" name"),
              correctAnswer = Some("the crown bottlecap"),
              dollarValue = Some(1600)
            ),
            JeopardyQuestion(
              clue = Some("Inspired by a glass of beer, Donald Glaser made this device in 1952 to track cosmic rays"),
              correctAnswer = Some("the bubble chamber"),
              dollarValue = Some(2000)
            )
          )
        ),
        JeopardyCategory(
          title = "A WORLD OF POTPOURRI",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("Even the Duchess of Windsor is included on Royalty Row at the Royal London Wax Museum in this B.C. capital"),
              correctAnswer = Some("Victoria"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("Visit the graves of Mozart's wife & father at this city's St. Sebastian cemetery--but you won't find Mozart with them"),
              correctAnswer = Some("Salzburg"),
              dollarValue = Some(800)
            ),
            JeopardyQuestion(
              clue = Some("Since 1981 Queen Beatrix has lived at Huis Ten Bosch palace in this \"court\"ly city"),
              correctAnswer = Some("The Hague"),
              dollarValue = Some(1200)
            ),
            JeopardyQuestion(
              clue = Some("The name of this capital of Senegal comes from a Wolof word for the tamarind tree"),
              correctAnswer = Some("Dakar"),
              dollarValue = Some(1600)
            ),
            JeopardyQuestion(
              clue = Some("(Sofia of the Clue Crew stands with a mariachi band in the Yucatan Peninsula, Mexico.) " +
                "Add 3 letters to \"guitar\" to get the name of this bass guitar used in Mexican music"),
              correctAnswer = Some("guitarron"),
              dollarValue = Some(2000)
            )
          )
        ),
        JeopardyCategory(
          title = "LINGUA TECHNA",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("You can't dip them in milk, but these bits of information sent from web servers to browsers sure are sweet"),
              correctAnswer = Some("cookies"),
              dollarValue = Some(400)
            ),
            JeopardyQuestion(
              clue = Some("42.53.508.454 is an example of one of the numbers known by these 2 initials, which are basically web addresses"),
              correctAnswer = Some("IP"),
              dollarValue = Some(800)
            ),
            JeopardyQuestion(
              clue = Some("This adjective that precedes \"server\" refers to a server that is an intermediary between a user & a client"),
              correctAnswer = Some("proxy"),
              dollarValue = Some(1200)
            ),
            JeopardyQuestion(
              clue = Some("First developed by Xerox, this local access network technology sounds like a device to catch an anesthetic"),
              correctAnswer = Some("an ethernet cable"),
              dollarValue = Some(1600)
            ),
            JeopardyQuestion(
              clue = Some("Measured in bits per second, it's how much data can be sent through a connection (or the size of a ring)"),
              correctAnswer = Some("bandwidth"),
              dollarValue = Some(2000)
            )
          )
        )
      )
    ),
    thirdRound = JeopardyRound(
      round = Some(JeopardyRounds.FINAL),
      categories = Vector(
        JeopardyCategory(
          title = "FIRST LADIES",
          questions = Vector(
            JeopardyQuestion(
              clue = Some("She survived the President by 39 years & was married to an archaeology professor at the time of her own death in 1947"),
              correctAnswer = Some("Frances Folsom Cleveland"),
              dollarValue = None,
              isWager = true
            )
          )
        )
      )
    )
  )
}
