package dev.konnov.smartflashcards.app.data.datasource

import dev.konnov.smartflashcards.app.data.model.CardModel
import javax.inject.Inject

class CardDataSource @Inject constructor() {

    fun get(deckId: String, cardId: Int): CardModel =
        mockCards[cardId] ?: throw IllegalArgumentException("Card with id ${cardId} not found")

    private val mockCards = mapOf(
        1 to CardModel(
            "deck1",
            1,
            "Take the plunge",
            "делать решительный шаг\n" +
                    "\n" +
                    "commit oneself to a course of action about which one is nervous.\n" +
                    "\n" +
                    "\"she wondered whether to enter for the race, but decided to take the plunge\"\n" +
                    "\"They're finally taking the plunge and getting married.\"\n" +
                    "\n" +
                    "\n" +
                    "plunge:\n" +
                    "jump or dive quickly and energetically.\n" +
                    "\"our daughters whooped as they plunged into the sea\"\n"
        ),
        2 to CardModel(
            "deck1",
            2,
            "smug",
            "having or showing an excessive pride in oneself or one's achievements.\n" +
                    "самодовольный\n" +
                    "\n" +
                    "\"he was feeling smug after his win\"\n" +
                    "\"When my little brother was selected for the school swimming team, he became unbearably smug\"\n" +
                    "\n" +
                    "a smug grin洋洋得意的笑\n" +
                    "She deserved her promotion, but I wish she wasn't so smug about it.她應該得到晉升，但我希望她對此不要那麽沾沾自喜。\n" +
                    "There was a hint of smug self-satisfaction in her voice.她的言語中流露出一絲自鳴得意。\n" +
                    "He's been unbearably smug since he gave up smoking.他戒煙之後，那個得意的樣子簡直讓人難以忍受。\n" +
                    "\n" +
                    "Possibly from Middle Low German smuk (“lithe, delicate, neat, trim”) although the g of the English word is not easily explained. From the Low German derived also North Frisiansmok, Danish smuk and Swedish smukk (now obsolete or dialectal). The ultimate source should be Proto-Germanic *smeuganą.\n" +
                    "Compare Middle High German gesmuc (“ornament”) and smücken (“to dress, to adorn”), both ultimately from smiegen (“to press to, insert, wrap, to nestle”), hence Germanschmiegen, Schmuck and schmücken. The adjective schmuck, however, was borrowed from Low German. See smock for more."
        ),
        3 to CardModel(
            "deck1",
            3,
            "Psychic",
            "relating to or denoting faculties or phenomena that are apparently inexplicable by natural laws, especially involving telepathy or clairvoyance.\n" +
                    "\n" +
                    "\"Psychic powers\" - телепатические способности\n" +
                    "\n" +
                    "Madame Labinsky claimed to have psychic powers. As a result, many recently bereaved people came to see her in the hope of a message from their loved ones"
        ),
        4 to CardModel(
            "deck1",
            4,
            "toss-up",
            "the tossing of a coin to make a decision between two alternatives.\n" +
                    "\n" +
                    "Jenny was finding her social life in Edinburgh very exciting tonight it was a toss-up between going to a party and a concert"
        ),
        5 to CardModel(
            "deck1",
            5,
            "tacit",
            "understood or implied without being stated.\n" +
                    "\n" +
                    "your silence may be taken to mean tacit agreement\n" +
                    "\n" +
                    "The couple had a tacit understanding that they would vote for different candidates."
        ),
    )
}
