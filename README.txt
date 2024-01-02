Mistcraft: A Mistborn mod

This mod is inspired and based on the Mistborn book series by Brandon Sanderson

Adds new ores and feruchemy.

This is still a work-in-progress. More is coming.


Basics
**************
All of the required ores for the metallic arts generate underground. To find alluminum, you need to find bauxite ore in the dirt. Raw bauxite can be smelted into alumina, which can be smelted into aluminum ingots (I tried making it semi-realistic, while not being overcomplicated)

To make alloys, you need to use a metallurgy furnace. It is crafted with a blast furnace, steel, lava, and polished blackstone. To get the first ingots of steel needed to make your first metallurgy furnace, you'll have to look in loot chests of structures like pillager outposts, dungeons, and abandonded mineshafts.


Feruchemy
**************
When you spawn into a new world, you are given a random feruchemal power. You can use this command to see which power you have: /feruchemy get
If cheats are enabled, you can use /feruchemy give, /feruchemy remove, /feruchemy giveall, and /feruchemy clear to give one feruchemal power, revoke 1 power, give all powers, or remove all powers respectively.

To use feruchemy, craft a metalmind out of your metal. This is crafted with 4 ingots and 2 nuggets. Shift + right-click while holding to fill the metalmind, and right-click while holding to tap from the metalmind. Filling a metalmind increases its charge, and tapping it lowers the charge. There is a limit to how much you can store, but no limit to how much you can tap.

Checking a metalmind's charge can be done by hovering over it in the inventory and pressing shift.

Un-keyed metalminds
~~~~~~~~~~~~~~~~~~

By default, metalminds are keyed to the user, so only the person who fills it can use it.

To make a metalmind that anyone with the matching feruchemal power can use, you can make an unkeyed metalmind simply by filling an aluminum-mind* while using it. If the metalmind has already been keyed to you, it can't be unkeyed, so you need to start with an un-used metalmind.

You can check if a metalmind is unkeyed the same way you check its charge. If the UUID field is blank, it is unkeyed

Un-sealed metalminds
~~~~~~~~~~~~~~~~~~~
You can also make a metalmind that anyone can use, even if they aren't a feruchemist.

First, craft a nicrosil-lined metalmind using an empty metalmind, 4 nicrosil ingots, and 2 nicrosil nuggets. Any charge stored in the original metalmind will be lost when crafting into a nicrosil-lined metalmind, so make a new, empty metalmind for this.

Filling the metalmind can be done normally, and as long as nicrosil is one of your feruchemal powers, it will fill up the 'Investiture' charge alongside the normal charge. When an none feruchemist uses this metalmind, the 'Investiture' charge will go down. It can be used by non-feruchemists until the 'Investiture' charge runs out.

For anyone to be able to use it, you will also have to make it unkeyed(just start filling an aluminum mind before you start filling it).

Compounding
~~~~~~~~~~~~~~~~~~~
If you are tapping from a metalmind while also burning that same metal, your allomantic reserves are drained rather than your feruchemal stores. If you are storing in a metalmind while burning the same metal, storing is ten times faster and your allomantic reserves get drained. Compounding only works if you have the ability to use both the feruchemal and allomantic power for the same metal, so you can't compound with an unsealed metalmind unless you could use that type of metalmind normally.


Allomancy
**************
When you spawn in a world, you are given one random allomantic power. To check, use the '/allomancy get' command. Cheats don't need to be enabled
For the other allomancy commands, you do need cheats enabled. These commands are the same as the feruchemy commands, but for allomancy instead.

To use allomancy, you need to craft a metal shaping hammer out of three steel and two sticks. Combine it with a metal ingot to get metal beads. Right click to eat the beads.
Press the 'v' key to burn your metal. If you have multiple metals, a HUD will appear with buttons to click for burning your metals.

Flaring
~~~~~~~~~~~~~~~~~~~
Press ctrl while pressing 'v' or clicking the metal button to flare your metal. It will burn twice as fast, but the effects will be twice as strong. The burning indicator will also be blue, not yellow.

Duralumin/Nicrosil boosting
~~~~~~~~~~~~~~~~~~~
If you burn duralumin or are hit by someone burning nicrosil, all of your metals will be dramatically enhanced. This can have unexpected effects.

Steel Pushing & Iron Pulling
~~~~~~~~~~~~~~~~~~~
If you are burning iron/steel and move around while holding the alt key(or whatever key you set it to), you will pull/push off of nearby metal sources.
Burning steel while left-clicking with a nugget shoots the nugget as a projectile. If you're sneaking, you'll shoot a handful of coins
Right-clicking with iron allows you to pull metal items out of entities hands


Hemallurgy

**************

Spikes can be crafted from various metals. Each type of spike steals a different allomantic or feruchemic power when you kill a player with it. The powers that can be stolen with each type of spike are accurate with the books. Using an Invested (meaning it has been used to steal someone's power) spike grants you that power.Currently, no other attributes (like strength, identity, etc.) can be stolen with spikes. Eventually I might make this system better.