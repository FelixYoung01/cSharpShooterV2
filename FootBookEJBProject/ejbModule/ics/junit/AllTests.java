package ics.junit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ MatchJUnit.class, PitchJUnit.class, RefereeJUnit.class, RefereeLicenceJUnit.class, UserJUnit.class })
public class AllTests {

}
