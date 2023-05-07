# TFC-Passing-Days


## Explanation and examples

 Addon for TFC that adds varying day to night cycles based on the time of the year and Z coordinate. It tries to mimic the real behavior of day/night cycles from the real world. At z = 20k the day and night are both equal to 50% of the entire day and doesn't change based on the time of year, while at -20k the daytime can be as long as 90% of day or as low as 10% (at -20k they correspond to the summer solstice and the winter solstice, which are also situated roughly at 2/3 of Early Summer/Early Winter) and of course, any z position in between has intermediate values of day/night ration variations with them getting more tame as you approach z = 20k. If we consider the z = 20k line, the time/day night ratio variations are mirrored for increasing/decreasing z, so as opposed to the previous example with -20k, at 60k the 90% daytime mark is reached in the winter solstice and the 10% mark in the summer solstice (as in the south hemisphere of map), thus one can consider the range of z coordinates -20k to 60k (with the center at 20k) mimicking the day/night ratio variations of the north / south hemisphere. Outside of the -20k to 60k the day/night ratio variations just repeat. One thing to note is that the seasons in the 20k to 60k are not flipped, as they should be realistically, but they might with future updates. Another limitation is the fact that the sun rise is always happening at the same time of day every day.
 
## Compatibility

- Minecraft's day light sensor compatibility is covered by the mod.

- The time at which one player can go to bed is not affected by the mod nor the amount of time that is skipped when a player sleeps.

- If any other mod has day/night logistics within the tile entity of the respective block (most mods should do) then it will respond correctly to the modified day and night cycles.
